package team.bahor.services.exam.exam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import team.bahor.dto.exam.exam.*;
import team.bahor.dto.exam.examQuestion.ExamQuestionDto;
import team.bahor.dto.exam.examQuestionGeneration.ExamQuestionGenerationCreateDto;
import team.bahor.entity.exam.Exam;
import team.bahor.entity.exam.ExamQuestionUser;
import team.bahor.entity.exam.ExamUser;
import team.bahor.exeptions.exam.FinishDtoException;
import team.bahor.exeptions.exam.FinishTimeException;
import team.bahor.exeptions.exam.NotFoundExamException;
import team.bahor.mappers.exam.ExamMapper;
import team.bahor.repositories.exam.ExamQuestionUserRepository;
import team.bahor.repositories.exam.ExamRepository;
import team.bahor.repositories.exam.ExamUserRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.services.exam.examQuestion.ExamQuestionServiceImpl;
import team.bahor.services.exam.examQuestionGeneration.ExamQuestionGenerationServiceImpl;
import team.bahor.utils.Utils;
import team.bahor.utils.UtilsForSessionUser;
import team.bahor.validators.exam.ExamValidator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ExamServiceImpl extends AbstractService<
        ExamRepository,
        ExamMapper,
        ExamValidator
        > implements ExamService {


    private final ExamQuestionGenerationServiceImpl examQuestionGenerationService;
    private final ExamQuestionUserRepository examQuestionUserRepository;
    private final ExamQuestionServiceImpl examQuestionService;
    private final ExamUserRepository examUserRepository;
    private final UtilsForSessionUser utils;

    public ExamServiceImpl(ExamMapper mapper, @Lazy ExamValidator validator, ExamRepository repository, ExamQuestionGenerationServiceImpl examQuestionGenerationService, ExamQuestionUserRepository examQuestionUserRepository, ExamQuestionServiceImpl examQuestionService, ExamUserRepository examUserRepository, UtilsForSessionUser utils) {
        super(mapper, validator, repository);
        this.examQuestionGenerationService = examQuestionGenerationService;
        this.examQuestionUserRepository = examQuestionUserRepository;
        this.examQuestionService = examQuestionService;
        this.examUserRepository = examUserRepository;
        this.utils = utils;
    }

    @Override
    public String create(ExamCreateDtoBegin createDto) {
        validator.validOnCreate(createDto);
        Exam exam = mapper.fromCreateDto(createDto);
        exam.setStatus((short) 300);
        exam.setId(UUID.randomUUID().toString());
        return repository.save(exam).getId();
    }

    @Override
    public void delete(String id) {
        repository.deleted(id);
    }

    @Override
    public void update(ExamUpdateDto updateDto) {
        validator.validOnUpdate(updateDto);
        AtomicInteger questionCount = new AtomicInteger();
        AtomicInteger mark = new AtomicInteger();
        if (Objects.nonNull(updateDto.getQuestionCounts())) {
            updateDto.getQuestionCounts().forEach((k, v) -> {
                questionCount.addAndGet(Integer.parseInt(k));
                mark.addAndGet(Integer.parseInt(k) * v);
                examQuestionGenerationService.update(updateDto.getId(), k, v);
            });
        }

//        Exam exam = repository.findById(updateDto.getId()).get();
        Exam exam = repository.getByIdAndDeletedIsFalse(updateDto.getId());
//        Exam exam = repository.getById(updateDto.getId());
        Exam exam1 = mapper.fromUpdateDto(updateDto, exam);
        repository.save(exam1);

    }

    @Override
    public ExamDto get(String id) {
        //todo examdto ichiga couserseTitle qo'sh
        Optional<Exam> byIdAndDeletedFalse = repository.findByIdAndDeletedFalse(id);
        if (byIdAndDeletedFalse.isEmpty()) {
            throw new NotFoundExamException("Not found Exam");
        }
        ExamDto examDto = mapper.toDto(byIdAndDeletedFalse.get());
        Map<String, Integer> questionCount = new HashMap<>();
        examQuestionGenerationService.getAllByExamId(id).forEach(examQuestionGenerationDto -> questionCount.put(examQuestionGenerationDto.getMark(), examQuestionGenerationDto.getCount()));
        examDto.setQuestionCounts(questionCount);
        return examDto;
    }

    @Override
    public List<ExamDto> getAll() {
        return repository.findAllByDeletedFalse().parallelStream().map(exam -> {
            ExamDto examDto = mapper.toDto(exam);
            Map<String, Integer> questionCount = new HashMap<>();
            examQuestionGenerationService.getAllByExamId(exam.getId()).forEach(examQuestionGenerationDto ->
                    questionCount.put(examQuestionGenerationDto.getMark(), examQuestionGenerationDto.getCount()));
            examDto.setQuestionCounts(questionCount);
            return examDto;
        }).toList();
    }

    public String create(ExamCreateDtoEnd dto) {
        validator.createEnd(dto);
        ExamDto examDto = this.get(dto.getId());
        examDto.setDuration(dto.getDuration());
        AtomicInteger questionCount = new AtomicInteger();
        AtomicInteger mark = new AtomicInteger();
        dto.getQuestionCounts().forEach((k, v) -> {
            questionCount.addAndGet(Integer.parseInt(k));
            mark.addAndGet(Integer.parseInt(k) * v);
            examQuestionGenerationService.create(new ExamQuestionGenerationCreateDto(dto.getId(), v, Integer.parseInt(k)));
        });
        //todo save qilish uchun thread pool yarat
        examDto.setQuestionCount(questionCount.get());
        examDto.setMaxMark(mark.get());
        repository.update(examDto);
        return dto.getId();
    }

    public void block(String id) {
        repository.block(id);
    }

    public String createExamUser(String examId) {
        validator.createExamUser(examId);
        AtomicInteger count = new AtomicInteger(1);
        Exam exam = repository.getByIdAndDeletedIsFalse(examId);
        LocalDateTime finishingTime = LocalDateTime.now().plusSeconds(exam.getDuration() / 1000);
        ExamUser examUser = new ExamUser();
        String examUserId = UUID.randomUUID().toString();
        examUser.setId(examUserId);
        examUser.setExamId(examId);
        examUser.setUserId(Utils.getSessionId());
        examUser.setFinishingTime(finishingTime);
        examUser.setMark(exam.getMaxMark());
        examUser.setPercentage(0D);
        examUserRepository.save(examUser);
        examQuestionGenerationService.getAllByExamId(examId).parallelStream()
                .forEach(examQuestionGenerationDto ->
                        examQuestionService.getByExamId(examId, examQuestionGenerationDto.getMark(), examQuestionGenerationDto.getCount())
                                .parallelStream().forEach(examQuestionId -> {
                                    ExamQuestionUser examQuestionUser = new ExamQuestionUser();
                                    examQuestionUser.setId(UUID.randomUUID().toString());
                                    examQuestionUser.setExamUserId(examUserId);
                                    examQuestionUser.setOrderQuestion(count.getAndIncrement());
                                    examQuestionUser.setExamQuestionId(examQuestionId);
                                    examQuestionUserRepository.save(examQuestionUser);
                                }));
        return examUserId;
    }


    public InformationForCreateExamUser informationForCreateExamUser(String examId) {
        validator.informationForCreateExamUser(examId);
        InformationForCreateExamUser informationForCreateExamUser = new InformationForCreateExamUser();
        informationForCreateExamUser.setExamId(examId);
        informationForCreateExamUser.setQuestionCounts(examQuestionService.informationForCreateExamUser(examId));
        informationForCreateExamUser.setExamTitle(this.get(examId).getTitle());
        informationForCreateExamUser.setCourseTitle(this.get(examId).getCourseTitle());
        return informationForCreateExamUser;
    }

    public ExamSolveDto solve(ExamSolveDto exam) {
        validator.solve(exam);

        if (examUserRepository.findByExamUserIdForFinishingTime(exam.getExamUserId()).before(Timestamp.valueOf(LocalDateTime.now()))) {
            throw new FinishTimeException("Vaqt tugadi finishni bosing");
        }

        ExamSolveDto examSolveDto = new ExamSolveDto();
        Exam examByExamUserId = repository.getByExamUserId(exam.getExamUserId());

        if (Objects.isNull(exam.getNextOrder())) {
            exam.setNextOrder(1);
        }

        //todo thread pool yaratsang shuni methodni berib yubor
        if (Objects.nonNull(exam.getOrder()) && Objects.nonNull(exam.getMarkedAnswerId())) {
            this.saveExamSolveDto(exam);
        }

        examQuestionDto(exam, examSolveDto, examByExamUserId);
        return examSolveDto;
//        try {
//
//        } catch (Exception ex) {
//            throw new SolveBadCredentialException("Notogri Javob jonatishdingiz");
//        }

    }

    private void examQuestionDto(ExamSolveDto exam, ExamSolveDto examSolveDto, Exam examByExamUserId) {
        ExamQuestionDto examQuestionDto = examQuestionService.getByExamUserIdAndOrder(exam.getExamUserId(), exam.getNextOrder());
        examSolveDto.setExamUserId(exam.getExamUserId());
        //todo exam title qolib ketdi
        examSolveDto.setExamTitle(examByExamUserId.getTitle());
        examSolveDto.setFinishingTime(examUserRepository.findByExamUserIdForFinishingTime(exam.getExamUserId()).toLocalDateTime());
        examSolveDto.setOrder(exam.getNextOrder());
        examSolveDto.setQuestionDto(examQuestionDto);
    }

    private void saveExamSolveDto(ExamSolveDto exam) {
        examQuestionUserRepository.saveExamSolveDto(exam.getExamUserId(), exam.getOrder(), exam.getMarkedAnswerId());
    }

    public FinishDto finish(String examUserId) {
        validator.finish(examUserId);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(repository.finish(examUserId), FinishDto.class);
        } catch (JsonProcessingException e) {
            throw new FinishDtoException("Finish Dto Exception");
        }
    }

    public boolean isMakeExam(String courseId) {
        return repository.isMakeExam(courseId);
    }

    public boolean isCanCreateBegin(String sessionId, String couseId) {
        return repository.isCanCreateBegin(sessionId, couseId);
    }

    public boolean isTeacher(String sessionId, String id) {
        return repository.isTeacher(sessionId, id);
    }

    public boolean isThereCourse(String courseId) {
        return repository.isThereCourse(courseId);
    }

    public boolean isThereExam(String id) {
        return repository.isThereExam(id);
    }

    public boolean isThereExamAndNotBlockAndActive(String examId) {
        return repository.isThereExamAndNotBlockAndActive(examId);
    }

    public boolean isStudentOfCourse(String sessionId) {
        return repository.isStudentOfCourse(sessionId);
    }

    public boolean isCompleted(String sessionId) {
        return repository.isCompleted(sessionId);
    }

    public boolean isThereExamUser(String examUserId) {
        return repository.isThereExamUser(examUserId);
    }

    public boolean hasTime(String examUserId) {
        return repository.hasTime(examUserId);
    }

    public Integer maxOrder(String examUserId) {
        return repository.maxOrder(examUserId);
    }

    public Map<String, Object> createData(String courseId) {
        try {
            Map<String, Object> result = new HashMap<>();
            Certificate certificate = new ObjectMapper().readValue(repository.createData(utils.getSessionId(), courseId), Certificate.class);
            String data = repository.createData(utils.getSessionId(), courseId);
            System.out.println(data);
            result.put("certificate", certificate);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}


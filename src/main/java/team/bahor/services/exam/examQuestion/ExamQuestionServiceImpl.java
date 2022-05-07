package team.bahor.services.exam.examQuestion;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team.bahor.dto.exam.examQuestion.ExamQuestionCreateDto;
import team.bahor.dto.exam.examQuestion.ExamQuestionDto;
import team.bahor.dto.exam.examQuestion.ExamQuestionUpdateDto;
import team.bahor.entity.exam.ExamQuestion;
import team.bahor.enums.types.QuestionType;
import team.bahor.exeptions.exam.BadCredentialsUpdateException;
import team.bahor.mappers.exam.ExamQuestionMapper;
import team.bahor.repositories.exam.ExamQuestionRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.services.exam.answerToExamQuestion.AnswerToExamQuestionServiceImpl;
import team.bahor.validators.exam.ExamQuestionValidator;

import java.util.*;

@Service
public class ExamQuestionServiceImpl extends AbstractService<
        ExamQuestionRepository,
        ExamQuestionMapper,
        ExamQuestionValidator
        > implements ExamQuestionService {
    private final AnswerToExamQuestionServiceImpl answerToExamQuestionService;

    public ExamQuestionServiceImpl(@Qualifier("examQuestionMapperImpl") ExamQuestionMapper mapper, ExamQuestionValidator validator, ExamQuestionRepository repository, AnswerToExamQuestionServiceImpl answerToExamQuestionService) {
        super(mapper, validator, repository);
        this.answerToExamQuestionService = answerToExamQuestionService;
    }

    @Override
    public String create(ExamQuestionCreateDto createDto) {
        validator.validOnCreate(createDto);
        ExamQuestion examQuestion = mapper.fromCreateDto(createDto);
        examQuestion.setType(QuestionType.get(createDto.getType()));
        String id = UUID.randomUUID().toString();
        examQuestion.setId(id);
        repository.save(examQuestion);
        createDto.getAnswers().forEach(answerToExamQuestionCreateDto -> {
            answerToExamQuestionCreateDto.setExamQuestionId(id);
            answerToExamQuestionService.create(answerToExamQuestionCreateDto);
        });
        return id;
    }

    @Override
    public void delete(String id) {
        repository.deleted(id);
    }

    @Override
    public void update(ExamQuestionUpdateDto updateDto) {
        ExamQuestion examQuestion = repository.findById(updateDto.getId()).orElseThrow(() -> {
            throw new BadCredentialsUpdateException("not found question");
        });
        ExamQuestion examQuestion1 = mapper.fromUpdateDto(updateDto, examQuestion);
        repository.save(examQuestion1);
    }

    @Override
    public ExamQuestionDto get(String id) {
        validator.validateKey(id);
        ExamQuestionDto examQuestionDto = mapper.toDto(repository.getByIdAndDeletedIsFalse(id));
        examQuestionDto.setAnswers(answerToExamQuestionService.getAll(id));
        return examQuestionDto;
    }

    @Override
    public List<ExamQuestionDto> getAll() {
        return mapper.toDto(repository.getByDeletedFalse());
    }

    public void block(String id) {
        validator.block(id);
        repository.block(id);
    }

    public List<ExamQuestionDto> getAll(String id) {
        validator.getAll(id);
        return repository.getAllByExamIdAndDeletedFalseAndStatus(id).parallelStream().map(examQuestion -> {
            ExamQuestionDto examQuestionDto = mapper.toDto(examQuestion);
            examQuestionDto.setAnswers(answerToExamQuestionService.getAll(examQuestion.getId()));
            return examQuestionDto;
        }).toList();
    }


    public List<String> getByExamId(String examId, String mark, Integer count) {
        return repository.getByExamId(examId, mark, count);
    }

    public Map<String, Integer> informationForCreateExamUser(String examId) {
        Map<String, Integer> map = new HashMap<>();
        repository.informationForCreateExamUser(examId).forEach(objects -> {
            List<Object> objects1 = Arrays.stream(objects).toList();
            map.put(objects1.get(0).toString(), Integer.parseInt(objects1.get(1).toString()));
        });
        return map;
    }

    public ExamQuestionDto getByExamUserIdAndOrder(String examUserId, Integer nextOrder) {
        ExamQuestionDto examQuestionDto = mapper.toDto(repository.getByExamUserIdAndOrder(examUserId, nextOrder));
        examQuestionDto.setAnswers(answerToExamQuestionService.getAll(examQuestionDto.getId()));
        return examQuestionDto;
    }

    public boolean isTeacher(String examId, String sessionId) {
        return repository.isTeacher(examId, sessionId);
    }

    public boolean existsById(String id) {
        return repository.existsById(id);
    }
}

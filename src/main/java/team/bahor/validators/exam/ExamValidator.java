package team.bahor.validators.exam;

import org.springframework.stereotype.Component;
import team.bahor.dto.exam.exam.ExamCreateDtoBegin;
import team.bahor.dto.exam.exam.ExamCreateDtoEnd;
import team.bahor.dto.exam.exam.ExamSolveDto;
import team.bahor.dto.exam.exam.ExamUpdateDto;
import team.bahor.exeptions.ValidationException;
import team.bahor.exeptions.exam.*;
import team.bahor.services.course.CourseService;
import team.bahor.services.exam.exam.ExamServiceImpl;
import team.bahor.services.exam.examQuestionGeneration.ExamQuestionGenerationServiceImpl;
import team.bahor.utils.Utils;
import team.bahor.utils.UtilsForSessionUser;
import team.bahor.validators.base.AbstractValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ExamValidator extends AbstractValidator<ExamCreateDtoBegin, ExamUpdateDto, String> {
    private final CourseService courseService;
    private final ExamServiceImpl examService;
    private final ExamQuestionGenerationServiceImpl examQuestionGenerationService;
    private final UtilsForSessionUser utils;

    public ExamValidator(CourseService courseService, ExamServiceImpl examService, ExamQuestionGenerationServiceImpl examQuestionGenerationService, UtilsForSessionUser utils) {
        this.courseService = courseService;
        this.examService = examService;
        this.examQuestionGenerationService = examQuestionGenerationService;
        this.utils = utils;
    }

    @Override
    public void validateKey(String id) throws ValidationException {

    }

    @Override
    public void validOnCreate(ExamCreateDtoBegin examCreateDtoBegin) throws ValidationException {
        if (Objects.isNull(examCreateDtoBegin.getCourseId())) {
            throw new NotFoundExamCourseIdException("Not Found CourseId");
        }

        if (!examService.isThereCourse(examCreateDtoBegin.getCourseId())) {
            throw new NotFoundCourseException("Not found course");
        }

        if (examService.isMakeExam(examCreateDtoBegin.getCourseId())) {
            throw new AlreadySavedExamException("already saved exam");
        }

        if (!examService.isCanCreateBegin(utils.getSessionId(), examCreateDtoBegin.getCourseId())) {
            throw new YouCannotCreateException("you cannot create");
        }

        if (Objects.isNull(examCreateDtoBegin.getTitle())) {
            throw new NotFoundExamTitleException("Not Found Exam Title");
        }

    }

    @Override
    public void validOnUpdate(ExamUpdateDto cd) throws ValidationException {
        if (Objects.isNull(cd.getId())) {
            throw new NotFoundExamIdException("Not Found examId");
        }

        if (!examService.isThereExam(cd.getId())) {
            throw new NotFoundExamException("Not Found exam");
        }

        //todo
//        if (!examService.isTeacher(Utils.getSessionId(), cd.getId())) {
//            throw new YouCannotCreateException("you cannot update");
//        }

        if(Objects.nonNull(cd.getQuestionCounts())){
            AtomicBoolean atomicBoolean = new AtomicBoolean();
            Map<String, Integer> questionCount = new HashMap<>();
            examQuestionGenerationService.getAllByExamId(cd.getId()).forEach(examQuestionGenerationDto ->
                    questionCount.put(examQuestionGenerationDto.getMark(), examQuestionGenerationDto.getCount()));
            cd.getQuestionCounts().forEach((k, v) -> {
                if (Objects.nonNull(questionCount.get(k)) && questionCount.get(k) < v) {
                    atomicBoolean.set(true);
                }
            });
            if (atomicBoolean.get()) {
                throw new BadCredentialsInformationExam("Savollar soni koplik qildi yoki birorta savolga savol soni belgilanmansiz");
            }
        }
    }

    public void createEnd(ExamCreateDtoEnd dto) {
        if (Objects.isNull(dto.getId())) {
            throw new NotFoundExamIdException("Not Found examId");
        }

        if (!examService.isThereExam(dto.getId())) {
            throw new NotFoundExamException("Not Found exam");
        }

        if (!examService.isTeacher(Utils.getSessionId(), dto.getId())) {
            throw new YouCannotCreateException("you cannot create");
        }
        if (Objects.isNull(dto.getDuration()) || Objects.isNull(dto.getQuestionCounts())) {
            throw new BadCredentialsInformationExam("Bad Credentials Exam Create End");
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        Map<String, Integer> questionCount = new HashMap<>();
        examQuestionGenerationService.getAllByExamId(dto.getId()).forEach(examQuestionGenerationDto ->
                questionCount.put(examQuestionGenerationDto.getMark(), examQuestionGenerationDto.getCount()));
        dto.getQuestionCounts().forEach((k, v) -> {
            if (Objects.isNull(questionCount.get(k)) || questionCount.get(k) < v) {
                atomicBoolean.set(true);
            }
        });
        if (atomicBoolean.get()) {
            throw new BadCredentialsInformationExam("Savollar soni koplik qildi yoki birorta savolga savol soni belgilanmansiz");
        }
    }

    public void informationForCreateExamUser(String examId) {

        if ((!utils.hasRole("admin") || utils.hasRole("superAdmin") || examService.isTeacher(utils.getSessionId(), examId)))
            throw new BadCredentialsInformationExam("Sizda bunday role mavjud emas");

        if (!examService.isThereExam(examId)) {
            throw new NotFoundExamException("Not Found exam");
        }
    }

    public void createExamUser(String examId) {

        if (!examService.isThereExamAndNotBlockAndActive(examId)) {
            throw new NotFoundExamException("Not Found exam");
        }

        if (!examService.isStudentOfCourse(utils.getSessionId())) {
            throw new IsCanThisExam("siz bu exam ishla olmaysiz");
        }

        if (!examService.isCompleted(utils.getSessionId())) {
            throw new IsCanThisExam("siz bu exam tugatmagansiz");
        }
        //todo user bir marta imtihon topshirsin
    }

    public void solve(ExamSolveDto exam) {
        if (!examService.isThereExamUser(exam.getExamUserId())) {
            throw new IsThereExamUser("Bunday exam create qilmagan");
        }
        //todo next orderni tekshir jami order katta bolsa error qaytar
        if (examService.maxOrder(exam.getExamUserId()) < exam.getNextOrder() || exam.getNextOrder() <= 0) {
            throw new BadCredentialsSolve("bunday order yoq");
        }
    }

    public void finish(String examUserId) {
        if (!examService.isThereExamUser(examUserId)) {
            throw new IsThereExamUser("Bunday exam create qilmagan");
        }
    }
}

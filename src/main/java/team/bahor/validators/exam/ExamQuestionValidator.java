package team.bahor.validators.exam;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionCreateDto;
import team.bahor.dto.exam.examQuestion.ExamQuestionCreateDto;
import team.bahor.dto.exam.examQuestion.ExamQuestionUpdateDto;
import team.bahor.exeptions.ValidationException;
import team.bahor.exeptions.exam.*;
import team.bahor.services.exam.exam.ExamServiceImpl;
import team.bahor.services.exam.examQuestion.ExamQuestionServiceImpl;
import team.bahor.utils.Utils;
import team.bahor.utils.UtilsForSessionUser;
import team.bahor.validators.base.AbstractValidator;

import java.util.Objects;

@Component
public class ExamQuestionValidator extends AbstractValidator<
        ExamQuestionCreateDto,
        ExamQuestionUpdateDto,
        String
        > {

    private final ExamQuestionServiceImpl service;
    private final UtilsForSessionUser utils;
    private final ExamServiceImpl examService;

    public ExamQuestionValidator(@Lazy ExamQuestionServiceImpl service,@Lazy UtilsForSessionUser utils,@Lazy ExamServiceImpl examService) {
        this.service = service;
        this.utils = utils;
        this.examService = examService;
    }

    @Override
    public void validateKey(String id) throws ValidationException {
        if (!service.existsById(id)) {
            throw new NotFoundExamQuestion("Not found exam question");
        }
    }

    @Override
    public void validOnCreate(ExamQuestionCreateDto examQuestionCreateDto) throws ValidationException {
        if (!service.isTeacher(examQuestionCreateDto.getExamId(), utils.getSessionId())) {
            throw new YouCannotCreateException("you cannot create");
        }

        if (Objects.isNull(examQuestionCreateDto.getExamId())) {
            throw new NotFoundExamIdException("Not Found examId");
        }

        if (!examService.isThereExam(examQuestionCreateDto.getExamId())) {
            throw new NotFoundExamException("Not Found exam");
        }

        if (Objects.isNull(examQuestionCreateDto.getMark())){
            throw new BadCredentialsExamQuestionCreate("not found examID");
        }

        if (Objects.isNull(examQuestionCreateDto.getAnswers())){
            throw new BadCredentialsExamQuestionCreate("not found Answers");
        }

        int correctCount = (int) examQuestionCreateDto.getAnswers().stream().filter(AnswerToExamQuestionCreateDto::isCorrect).count();
        if (correctCount != 1) {
            throw new NotValidOnCreateException("Not Valid On Create");
        }

    }


    @Override
    public void validOnUpdate(ExamQuestionUpdateDto dto) throws ValidationException {
        if(Objects.isNull(dto.getId())){
            throw new BadCredentialsUpdateException("not found id");
        }
    }

    public void block(String id) {

    }

    public void getAll(String id) {

    }
}

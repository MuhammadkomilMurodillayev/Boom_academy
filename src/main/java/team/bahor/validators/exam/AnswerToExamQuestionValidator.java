package team.bahor.validators.exam;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionCreateDto;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionUpdateDto;
import team.bahor.exeptions.ValidationException;
import team.bahor.exeptions.exam.PermissionDeniedException;
import team.bahor.services.exam.answerToExamQuestion.AnswerToExamQuestionServiceImpl;
import team.bahor.validators.base.AbstractValidator;

@Component
public class AnswerToExamQuestionValidator extends AbstractValidator<
        AnswerToExamQuestionCreateDto,
        AnswerToExamQuestionUpdateDto,
        String
        > {
    private final AnswerToExamQuestionServiceImpl service;

    public AnswerToExamQuestionValidator(@Lazy AnswerToExamQuestionServiceImpl service) {
        this.service = service;
    }

    @Override
    public void validateKey(String id) throws ValidationException {
        if (!service.isTeacher(id)) {
            throw new PermissionDeniedException("permission denied");
        }
    }

    @Override
    public void validOnCreate(AnswerToExamQuestionCreateDto answerToExamQuestionCreateDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(AnswerToExamQuestionUpdateDto cd) throws ValidationException {

    }

    public void deleted(String id) {
        if (!service.isTeacher(id)) {
            throw new PermissionDeniedException("permission denied");
        }
    }
}

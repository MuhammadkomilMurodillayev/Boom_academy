package team.bahor.validators.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionCreateDto;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionUpdateDto;
import team.bahor.entity.lesson.AnswerLessonQuestion;
import team.bahor.entity.lesson.LessonQuestion;
import team.bahor.entity.user.AuthUser;
import team.bahor.exeptions.ValidationException;
import team.bahor.exeptions.lesson.LessonNotFoundException;
import team.bahor.exeptions.lesson.answer.AnswerLessonQuestionNotFoundException;
import team.bahor.exeptions.user.AuthUserNotFoundExeption;
import team.bahor.repositories.auth.AuthUserRepository;
import team.bahor.repositories.lesson.AnswerLessonQuestionRepository;
import team.bahor.repositories.lesson.LessonQuestionRepository;
import team.bahor.utils.Utils;
import team.bahor.validators.base.AbstractValidator;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AnswerLessonQuestionValidator extends AbstractValidator<AnswerLessonQuestionCreateDto, AnswerLessonQuestionUpdateDto, String> {

    private final AnswerLessonQuestionRepository answerLessonQuestionRepository;
    private final LessonQuestionRepository lessonQuestionRepository;
    private final AuthUserRepository authUserRepository;

    @Override
    public void validateKey(String id) throws ValidationException {
        Optional<AnswerLessonQuestion> byAnswerId = answerLessonQuestionRepository.findByAnswerId(id, Utils.getSessionId());
        if (byAnswerId.isEmpty())
            throw new AnswerLessonQuestionNotFoundException("Answer Lesson Question Not Found !");

        activeAuthUser();
    }

    @Override
    public void validOnCreate(AnswerLessonQuestionCreateDto answerLessonQuestionCreateDto) throws ValidationException {
        validQuestionId(answerLessonQuestionCreateDto.getLessonQuestionId());
    }

    @Override
    public void validOnUpdate(AnswerLessonQuestionUpdateDto cd) throws ValidationException {
        validateKey(cd.getId());
    }

    public void validQuestionId(String questionId) throws ValidationException{
        Optional<LessonQuestion> byQuestionId = lessonQuestionRepository.findByQuestionId(questionId);
        if(byQuestionId.isEmpty())
            throw new LessonNotFoundException("Lesson Question Not Found !");
        activeAuthUser();
    }

    private void activeAuthUser() {
        AuthUser byIdAuthorizated = authUserRepository.findByIdAuthorizated(Utils.getSessionId());
        if (Objects.isNull(byIdAuthorizated))
            throw new AuthUserNotFoundExeption("Auth User Not Found");
    }
}

package team.bahor.validators.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.bahor.dto.lesson.question.LessonQuestionCreateDto;
import team.bahor.dto.lesson.question.LessonQuestionUpdateDto;
import team.bahor.entity.courses.CourseUser;
import team.bahor.entity.lesson.Lesson;
import team.bahor.entity.lesson.LessonQuestion;
import team.bahor.entity.user.AuthUser;
import team.bahor.exeptions.ValidationException;
import team.bahor.exeptions.course.CourseForbiddenException;
import team.bahor.exeptions.lesson.LessonNotFoundException;
import team.bahor.exeptions.lesson.question.LessonQuestionNotFoundException;
import team.bahor.exeptions.user.AuthUserNotFoundExeption;
import team.bahor.repositories.auth.AuthUserRepository;
import team.bahor.repositories.course.CourseUserRepository;
import team.bahor.repositories.lesson.LessonQuestionRepository;
import team.bahor.repositories.lesson.LessonRepository;
import team.bahor.utils.Utils;
import team.bahor.validators.base.AbstractValidator;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LessonQuestionValidator extends AbstractValidator<LessonQuestionCreateDto, LessonQuestionUpdateDto, String> {

    private final LessonRepository lessonRepository;
    private final CourseUserRepository courseUserRepository;
    private final LessonQuestionRepository lessonQuestionRepository;
    private final AuthUserRepository authUserRepository;


    @Override
    public void validateKey(String id) throws ValidationException {
        Optional<LessonQuestion> optionalLessonQuestion = lessonQuestionRepository.findByQuestionId(id, Utils.getSessionId());
        if (optionalLessonQuestion.isEmpty())
            throw new LessonQuestionNotFoundException("Lesson Question Not Found");

        activeAuthUser();

    }

    private void activeAuthUser() {
        AuthUser byIdAuthorizated = authUserRepository.findByIdAuthorizated(Utils.getSessionId());
        if (Objects.isNull(byIdAuthorizated))
            throw new AuthUserNotFoundExeption("Auth User Not Found");
    }

    @Override
    public void validOnCreate(LessonQuestionCreateDto lessonQuestionCreateDto) throws ValidationException {
        Optional<CourseUser> optionalCourseUser = courseUserRepository.findByLessonIdAndUserId(lessonQuestionCreateDto.getLessonId(), Utils.getSessionId());
        if (optionalCourseUser.isEmpty())
            throw new CourseForbiddenException("You cannot write a question to the lesson !");

        Optional<Lesson> optionalLesson = lessonRepository.findByLessonId(lessonQuestionCreateDto.getLessonId());
        if (optionalLesson.isEmpty())
            throw new LessonNotFoundException("Lesson Not Found");
    }

    @Override
    public void validOnUpdate(LessonQuestionUpdateDto cd) throws ValidationException {
        validateKey(cd.getId());
    }

    public void validateLessonKey(String lessonId) throws ValidationException {
        Optional<Lesson> optionalLesson = lessonRepository.findByLessonId(lessonId);
        if (optionalLesson.isEmpty())
            throw new LessonNotFoundException("Lesson Not Found");

        activeAuthUser();
    }

}

package team.bahor.validators.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.bahor.dto.lesson.note.NotesToLessonsCreateDto;
import team.bahor.dto.lesson.note.NotesToLessonsUpdateDto;
import team.bahor.entity.lesson.Lesson;
import team.bahor.entity.lesson.NotesToLessons;
import team.bahor.entity.user.AuthUser;
import team.bahor.exeptions.ValidationException;
import team.bahor.exeptions.lesson.LessonNotFoundException;
import team.bahor.exeptions.lesson.notes.NotesToLessonsNotFoundException;
import team.bahor.exeptions.user.AuthUserNotFoundExeption;
import team.bahor.repositories.auth.AuthUserRepository;
import team.bahor.repositories.lesson.LessonRepository;
import team.bahor.repositories.lesson.NotesToLessonsRepository;
import team.bahor.utils.Utils;
import team.bahor.validators.base.AbstractValidator;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NotesToLessonsValidator extends AbstractValidator<NotesToLessonsCreateDto, NotesToLessonsUpdateDto, String> {

    private final AuthUserRepository authUserRepository;
    private final NotesToLessonsRepository notesToLessonsRepository;
    private final LessonRepository lessonRepository;

    @Override
    public void validateKey(String id) throws ValidationException {
        validAuthUserAccount(Utils.getSessionId());

        Optional<NotesToLessons> optionalNotesToLessons = notesToLessonsRepository.findByNotesToLessonsId(id, Utils.getSessionId());
        if (optionalNotesToLessons.isEmpty())
            throw new NotesToLessonsNotFoundException("Notes To Lessons Not Found !");
    }

    @Override
    public void validOnCreate(NotesToLessonsCreateDto notesToLessonsCreateDto) throws ValidationException {
        validAuthUserAccount(notesToLessonsCreateDto.getCreatedBy());

        Optional<Lesson> lesson = lessonRepository.takeThisCourse(notesToLessonsCreateDto.getLessonId(), Utils.getSessionId());
        if (lesson.isEmpty())
            throw new LessonNotFoundException("Lesson Not Found !");


    }

    @Override
    public void validOnUpdate(NotesToLessonsUpdateDto cd) throws ValidationException {
        validAuthUserAccount(Utils.getSessionId());

        Optional<NotesToLessons> optionalNotesToLessons = notesToLessonsRepository.findByNotesToLessonsId(cd.getId(), Utils.getSessionId());
        if (optionalNotesToLessons.isEmpty())
            throw new NotesToLessonsNotFoundException("Notes To Lessons Not Found !");
    }

    public void validAuthUserAccount(String userId) throws ValidationException {
        AuthUser byIdAuthorizated = authUserRepository.findByIdAuthorizated(userId);
        if (Objects.isNull(byIdAuthorizated))
            throw new AuthUserNotFoundExeption("Forbidden !");
    }
}

package team.bahor.validators.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import team.bahor.dto.lesson.LessonCreateDto;
import team.bahor.dto.lesson.LessonUpdateDto;
import team.bahor.exeptions.AccessDeniedException;
import team.bahor.exeptions.ValidationException;
import team.bahor.exeptions.course.CategoryNotAvailableException;
import team.bahor.repositories.course.SectionRepository;
import team.bahor.utils.Utils;
import team.bahor.validators.base.AbstractValidator;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class LessonValidator extends AbstractValidator<LessonCreateDto, LessonUpdateDto, String> {

    private final SectionRepository sectionRepository;

    public boolean checkFile(MultipartFile multipartFile) {
        return Objects.requireNonNull(multipartFile.getContentType()).startsWith("video");
    }

    @Override
    public void validateKey(String id) throws ValidationException {

    }

    @Override
    public void validOnCreate(LessonCreateDto dto) throws ValidationException, CategoryNotAvailableException {
        hasAccess(dto);
    }

    private void hasAccess(LessonCreateDto dto) {
        if (sectionRepository.isUserCreatorOfCourse(dto.getSectionId(), Utils.getSessionId())
                || Utils.sessionHasRole("ADMIN"))
            throw new AccessDeniedException("DO_NOT_HAVE_PERMISSION_TO_CREATE_LESSON");
    }

    @Override
    public void validOnUpdate(LessonUpdateDto cd) throws ValidationException {

    }


}

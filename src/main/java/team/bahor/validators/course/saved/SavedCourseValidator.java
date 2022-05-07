package team.bahor.validators.course.saved;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.bahor.dto.course.saved.SavedCourseCreateDto;
import team.bahor.dto.course.saved.SavedCourseUpdateDto;
import team.bahor.exeptions.ValidationException;
import team.bahor.exeptions.course.saved.SavedCourseNotFoundException;
import team.bahor.exeptions.course.saved.SavedForbiddenException;
import team.bahor.repositories.auth.AuthUserRepository;
import team.bahor.repositories.course.SavedCourseRepository;
import team.bahor.utils.Utils;
import team.bahor.validators.base.AbstractValidator;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SavedCourseValidator extends AbstractValidator<SavedCourseCreateDto, SavedCourseUpdateDto, String> {

    private final SavedCourseRepository savedCourseRepository;
    private final AuthUserRepository authUserRepository;

    @Override
    public void validateKey(String id) throws ValidationException {
        if(Objects.isNull(authUserRepository.findByIdAuthorizated(Utils.getSessionId())))
            throw new SavedForbiddenException("NOT ALLOWED");
        if(Objects.isNull(savedCourseRepository.findByIdNoDelete(id)))
            throw new SavedCourseNotFoundException("Not Found");

    }

    @Override
    public void validOnCreate(SavedCourseCreateDto dto) throws ValidationException {

    }


    public boolean validOnCreated(SavedCourseCreateDto savedCourseCreateDto) throws ValidationException {
        return Objects.nonNull(savedCourseRepository.findByCourseIdAndUserId(savedCourseCreateDto.getUserId(), savedCourseCreateDto.getCourseId()));
    }

    @Override
    public void validOnUpdate(SavedCourseUpdateDto cd) throws ValidationException {

    }

    public void  validOnCreatedUser(String userId) throws ValidationException{
        if (!userId.equals(Utils.getSessionId()) || Objects.isNull(authUserRepository.findByIdAuthorizated(Utils.getSessionId())))
            throw new SavedForbiddenException("NOT ALLOWED");
    }
}

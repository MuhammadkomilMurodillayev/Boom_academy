package team.bahor.validators.course;

import org.springframework.stereotype.Component;
import team.bahor.dto.course.CourseCreateDto;
import team.bahor.dto.course.CourseUpdateDto;
import team.bahor.entity.courses.Course;
import team.bahor.enums.CourseCategory;
import team.bahor.enums.Role;
import team.bahor.exeptions.NotAllowedException;
import team.bahor.exeptions.ValidationException;
import team.bahor.exeptions.course.CategoryNotAvailableException;
import team.bahor.exeptions.course.CourseForbiddenException;
import team.bahor.exeptions.course.CourseNotFoundException;
import team.bahor.repositories.course.CourseRepository;
import team.bahor.utils.Utils;
import team.bahor.validators.base.AbstractValidator;

import java.util.Optional;

@Component
public class CourseValidator extends AbstractValidator<
        CourseCreateDto,
        CourseUpdateDto,
        String> {

    private final CourseRepository repository;

    public CourseValidator(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validateKey(String id) throws ValidationException {
        Optional<Course> courseOptional = repository.findByIdAndCreatedByAndDeletedFalse(id, Utils.getSessionId());
        if (courseOptional.isEmpty() && !Utils.sessionHasAnyRole(Role.SUPER_ADMIN.name(), Role.ADMIN.name()))
            throw new CourseNotFoundException("Course not found");

    }

    @Override
    public void validOnCreate(CourseCreateDto courseCreateDto) throws ValidationException{

        if (!CourseCategory.getAll().contains(CourseCategory.valueOf(courseCreateDto.getCategory())))
            throw new CategoryNotAvailableException("Not available");
        if (courseCreateDto.getPrice() < 0 || courseCreateDto.getDuration() < 0)
            throw new CourseForbiddenException("Not possible to enter a negative number");
    }

    @Override
    public void validOnUpdate(CourseUpdateDto dto) throws ValidationException {
        if (!CourseCategory.getAll().contains(CourseCategory.valueOf(dto.getCategory())))
            throw new CategoryNotAvailableException("Not available");
        if (dto.getPrice() < 0 || dto.getDuration() < 0)
            throw new CourseForbiddenException("Not possible to enter a negative number");

    }

    @Override
    public void validPermission(String... roles) throws ValidationException{
        if (!Utils.sessionHasAnyRole(roles))
            throw new NotAllowedException("You have not this permission");
    }
}

package team.bahor.validators.course.rating;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.bahor.dto.course.rating.CourseRatingCreateDto;
import team.bahor.dto.course.rating.CourseRatingUpdateDto;
import team.bahor.entity.courses.Course;
import team.bahor.entity.courses.CourseRating;
import team.bahor.exeptions.ValidationException;
import team.bahor.exeptions.course.CourseNotFoundException;
import team.bahor.exeptions.course.rating.CourseRatingInvalidException;
import team.bahor.exeptions.course.rating.CourseRatingNotFoundException;
import team.bahor.exeptions.course.rating.CourseRatingUserMarkException;
import team.bahor.exeptions.course.section.SectionForbiddenException;
import team.bahor.repositories.auth.AuthUserRepository;
import team.bahor.repositories.course.CourseRatingRepository;
import team.bahor.repositories.course.CourseRepository;
import team.bahor.repositories.course.CourseUserRepository;
import team.bahor.utils.Utils;
import team.bahor.validators.base.AbstractValidator;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CourseRatingValidator extends AbstractValidator<CourseRatingCreateDto, CourseRatingUpdateDto, String> {

    private final CourseUserRepository courseUserRepository;
    private final CourseRatingRepository courseRatingRepository;
    private final CourseRepository courseRepository;
    private final AuthUserRepository authUserRepository;

    @Override
    public void validateKey(String id) throws ValidationException {
        Optional<CourseRating> courseRatingOptional = courseRatingRepository.courseByIdAndUserId(id, Utils.getSessionId());
        if (courseRatingOptional.isEmpty())
            throw new CourseRatingNotFoundException("Not Found !");
    }

    @Override
    public void validOnCreate(CourseRatingCreateDto courseRatingCreateDto) throws ValidationException {
        if (0 >= courseRatingCreateDto.getRating() || courseRatingCreateDto.getRating() > 5)
            throw new CourseRatingInvalidException("Rating Data Invalid !");
        if (Objects.isNull(courseUserRepository.findByCourseIdAndUserIdAndStatus(courseRatingCreateDto.getCourseId(), courseRatingCreateDto.getUserId())))
            throw new CourseRatingUserMarkException("This User Is Not Allowed To Rate The Course");
    }

    @Override
    public void validOnUpdate(CourseRatingUpdateDto cd) throws ValidationException {
        if (cd.getRating() <= 0 || 5 < cd.getRating())
            throw new CourseRatingInvalidException("Rating Data Invalid !");

        Optional<CourseRating> courseRating = courseRatingRepository.courseById(cd.getId());
        if (courseRating.isEmpty())
            throw new CourseRatingNotFoundException("Not Found !");

        if (!courseRating.get().getUserId().equals(Utils.getSessionId()) || Objects.isNull(courseUserRepository.findByCourseIdAndUserIdAndStatus(courseRating.get().getCourseId(), courseRating.get().getUserId())))
            throw new CourseRatingUserMarkException("This User Is Not Allowed To Rate The Course");

    }


    public Course validateKeyCourse(String id) throws ValidationException{
        Optional<Course> courseOptional = courseRepository.activeThisCourse(id);
        if (courseOptional.isEmpty())
            throw new CourseNotFoundException("Course Not Found !");

        return courseOptional.get();
    }

    public void findByIdAuthorizated() throws ValidationException{
        if (Objects.isNull(authUserRepository.findByIdAuthorizated(Utils.getSessionId())))
            throw new SectionForbiddenException("Not allowed");

    }

}

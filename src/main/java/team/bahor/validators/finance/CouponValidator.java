package team.bahor.validators.finance;

import org.springframework.stereotype.Component;
import team.bahor.dto.finance.CouponCreateDto;
import team.bahor.dto.finance.CouponUpdateDto;
import team.bahor.entity.courses.Course;
import team.bahor.enums.Role;
import team.bahor.exeptions.NotAllowedException;
import team.bahor.exeptions.ValidationException;
import team.bahor.repositories.course.CourseRepository;
import team.bahor.utils.Utils;
import team.bahor.validators.base.AbstractValidator;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class CouponValidator extends AbstractValidator<CouponCreateDto, CouponUpdateDto, String> {

    private final CourseRepository courseRepository;

    public CouponValidator(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public void validateKey(String id) throws ValidationException {

    }

    @Override
    public void validOnCreate(CouponCreateDto dto) throws ValidationException {
        Optional<Course> courseOptional = courseRepository.findByIdAndCreatedByAndDeletedFalse(dto.getCourseId(), Utils.getSessionId());
        if (courseOptional.isEmpty()) {

            if (Utils.sessionHasAnyRole(Role.MANAGER.name(), Role.SUPER_ADMIN.name())) {
                courseOptional = courseRepository.findByIdAndDeletedFalse(dto.getCourseId());
            } else
                throw new NotAllowedException("bad request");

        }

        Course course = courseOptional.get();

        if (dto.getDiscountAmount() > course.getPrice())
            throw new NotAllowedException("the discount price should not exceed the course price");

        if (course.getPrice() > 0) {
            if (dto.getDiscountPercentage() != 0 && dto.getDiscountAmount() != 0)
                throw new NotAllowedException("Enter the course discount price as a percentage or number(select only one)");

            if (!dto.getDueDate().isAfter(LocalDate.now()))
                throw new NotAllowedException("please enter the next time");

            if (dto.getDiscountAmount() != 0) {
                dto.setDiscountPercentage((dto.getDiscountAmount() * 100) / course.getPrice());
            } else
                dto.setDiscountAmount(dto.getDiscountPercentage() * course.getPrice() / 100);

        } else {
            throw new NotAllowedException("you arent create coupon for this course, because this course free");
        }

    }

    @Override
    public void validOnUpdate(CouponUpdateDto cd) throws ValidationException {

    }

}

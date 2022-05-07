package team.bahor.validators.course.purchase;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import team.bahor.dto.course.purchase.CoursePurchaseCreateDto;
import team.bahor.dto.course.purchase.CoursePurchaseUpdateDto;
import team.bahor.entity.courses.Course;
import team.bahor.entity.finance.Coupon;
import team.bahor.exeptions.ValidationException;
import team.bahor.exeptions.course.CourseNotFoundException;
import team.bahor.exeptions.course.purchase.CoursePurchaseAlreadyTakedException;
import team.bahor.exeptions.finance.coupon.CouponNotFoundException;
import team.bahor.exeptions.user.AuthUserBalanceNoValidException;
import team.bahor.repositories.auth.AuthUserRepository;
import team.bahor.repositories.course.CoursePurchaseHistoryRepository;
import team.bahor.repositories.course.CourseRepository;
import team.bahor.repositories.finance.CouponRepository;
import team.bahor.utils.Utils;
import team.bahor.validators.base.AbstractValidator;

import java.util.Objects;
import java.util.Optional;

@Component
public class CoursePurchaseValidator extends AbstractValidator<
        CoursePurchaseCreateDto,
        CoursePurchaseUpdateDto,
        String> {

    private final CouponRepository couponRepository;
    private final CourseRepository courseRepository;
    private final AuthUserRepository authUserRepository;
    private final CoursePurchaseHistoryRepository coursePurchaseHistoryRepository;

    public CoursePurchaseValidator(CouponRepository couponRepository, CourseRepository courseRepository, AuthUserRepository authUserRepository, CoursePurchaseHistoryRepository coursePurchaseHistoryRepository) {
        this.couponRepository = couponRepository;
        this.courseRepository = courseRepository;
        this.authUserRepository = authUserRepository;
        this.coursePurchaseHistoryRepository = coursePurchaseHistoryRepository;
    }

    @Override
    public void validateKey(String id) throws ValidationException {
        if (id.equals(Utils.getSessionId()) && Objects.isNull(authUserRepository.findByIdAuthorizated(id)))
            throw new UsernameNotFoundException("User Not Fond or No Active");
    }

    @Override
    public void validOnCreate(CoursePurchaseCreateDto coursePurchaseCreateDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(CoursePurchaseUpdateDto cd) throws ValidationException {

    }

    public double validateCourse(String id) throws ValidationException {
        Optional<Course> courseOptional = courseRepository.activeThisCourse(id);
        if (courseOptional.isEmpty())
            throw new CourseNotFoundException("Course Not Found !");

        if(coursePurchaseHistoryRepository.findByUserIdAndCourseId(Utils.getSessionId(), id).isEmpty())
            throw new CoursePurchaseAlreadyTakedException("This Course Already Taked !");

        return courseOptional.get().getPrice();
    }


    public Coupon validCode(String code) throws ValidationException {
        Optional<Coupon> couponAmount = couponRepository.getCouponAmount(code);
        if (couponAmount.isPresent())
            return couponAmount.get();
        throw new CouponNotFoundException("Coupon Not Found !");
    }

    public double validUserBalance(double nativePrice) throws ValidationException {
        double balance = -1;
        balance = authUserRepository.validUserBalance(nativePrice, Utils.getSessionId());
        if (balance < 0)
            throw new AuthUserBalanceNoValidException("There is not enough money in your account !");
        return balance;
    }

}

package team.bahor.services.course.purchase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team.bahor.dto.course.purchase.CoursePurchaseCreateDto;
import team.bahor.dto.course.purchase.CoursePurchaseDto;
import team.bahor.dto.course.purchase.CoursePurchaseUpdateDto;
import team.bahor.entity.courses.CoursePurchaseHistory;
import team.bahor.entity.finance.Coupon;
import team.bahor.mappers.course.CoursePurchaseMapper;
import team.bahor.repositories.auth.AuthUserRepository;
import team.bahor.repositories.course.CoursePurchaseRepository;
import team.bahor.repositories.finance.CouponRepository;
import team.bahor.repositories.finance.DiscountRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.utils.Utils;
import team.bahor.validators.course.purchase.CoursePurchaseValidator;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class CoursePurchaseServiceImpl extends AbstractService<
        CoursePurchaseRepository,
        CoursePurchaseMapper,
        CoursePurchaseValidator> implements CoursePurchaseService {

    private final CouponRepository couponRepository;
    private final DiscountRepository discountRepository;
    private final AuthUserRepository authUserRepository;

    protected CoursePurchaseServiceImpl(@Qualifier("coursePurchaseMapperImpl") CoursePurchaseMapper mapper, CoursePurchaseValidator validator, CoursePurchaseRepository repository, CouponRepository couponRepository, DiscountRepository discountRepository, AuthUserRepository authUserRepository) {
        super(mapper, validator, repository);
        this.couponRepository = couponRepository;
        this.discountRepository = discountRepository;
        this.authUserRepository = authUserRepository;
    }

    @Override
    public String create(CoursePurchaseCreateDto createDto) {
        String couponId = null;
        double couponDiscountAmount = 0;
        double discountAmount = 0;
        double price = validator.validateCourse(createDto.getCourseId());
        double nativeCourseAmount = 0;

        validator.validateKey(createDto.getUserId());

        //first version on discount_amount
        if (Objects.nonNull(createDto.getCouponCode())) {
            Coupon coupon = validator.validCode(createDto.getCouponCode());
            couponDiscountAmount = coupon.getDiscountAmount();
            couponId = coupon.getId();
        }

        discountAmount = price * discountRepository.getCouponDiscountAmount(createDto.getCourseId()) / 100;
        nativeCourseAmount = price - couponDiscountAmount - discountAmount;

        double userBalance = validator.validUserBalance(nativeCourseAmount);

        if (couponDiscountAmount > 0)
            couponRepository.updateCouponLimit(createDto.getCouponCode());

        CoursePurchaseHistory purchaseHistory = new CoursePurchaseHistory();
        purchaseHistory.setCourseId(createDto.getCourseId());
        purchaseHistory.setCouponId(couponId);
        purchaseHistory.setUserId(createDto.getUserId());
        purchaseHistory.setDiscount(discountAmount);
        purchaseHistory.setPaymentAmount(nativeCourseAmount);
        repository.save(purchaseHistory);
        authUserRepository.changeUserBalance(userBalance - nativeCourseAmount, Utils.getSessionId());

        return "Saved";
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(CoursePurchaseUpdateDto updateDto) {

    }

    @Override
    public CoursePurchaseDto get(String id) {
        return null;
    }

    @Override
    public List<CoursePurchaseDto> getAll() {
        return null;
    }

    public List<CoursePurchaseDto> getAll(String userId) {
        validator.validateKey(userId);
        List<CoursePurchaseHistory> allUserCoursePurchase = repository.findAllUserCoursePurchase(userId);
        return mapper.toDto(allUserCoursePurchase);
    }

}

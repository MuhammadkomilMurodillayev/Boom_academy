package team.bahor.repositories.finance;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.bahor.entity.finance.Coupon;
import team.bahor.repositories.base.AbstractRepository;
import team.bahor.repositories.base.BaseGenericRepository;

import java.util.Optional;

@Repository
public interface CouponRepository extends AbstractRepository<Coupon, String> {
    Optional<Coupon> findByIdAndDeletedFalse(String id);
    @Query(value = "select * from boom_academy.main.coupon " +
            "where code = ?1 and is_deleted = 0 and limit_size > 0 and due_date < now() and status = 0",
            nativeQuery = true)
    Optional<Coupon> getCouponAmount(String couponCode);

    @Modifying
    @Query(value = "update boom_academy.main.coupon set limit_size = limit_size - 1 where code = ?1 and is_deleted = 0 and limit_size > 0 and due_date < now()", nativeQuery = true)
    void updateCouponLimit(String couponCode);
}

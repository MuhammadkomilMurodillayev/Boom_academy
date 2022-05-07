package team.bahor.repositories.finance;

import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.finance.Discount;
import team.bahor.repositories.base.AbstractRepository;

public interface DiscountRepository extends AbstractRepository<Discount, String> {
    @Query(value = "select percentage from boom_academy.main.discount where course_id = ?1 and  is_deleted = 0 and status = 0 and due_date > now() and limit_size > 0", nativeQuery = true)
    double getCouponDiscountAmount(String courseId);
}

package team.bahor.repositories.course;

import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.courses.Course;
import team.bahor.entity.courses.CoursePurchaseHistory;
import team.bahor.repositories.base.AbstractRepository;

import java.util.List;

public interface CoursePurchaseRepository extends AbstractRepository<CoursePurchaseHistory, String> {

    @Query(value = "select * from boom_academy.main.course_purchase_history where user_id = ?1 and is_deleted = 0 and status = 0", nativeQuery = true)
    List<CoursePurchaseHistory> findAllUserCoursePurchase(String userId);
}

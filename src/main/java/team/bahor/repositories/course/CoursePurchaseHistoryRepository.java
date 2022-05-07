package team.bahor.repositories.course;

import team.bahor.entity.courses.CoursePurchaseHistory;
import team.bahor.repositories.base.AbstractRepository;

import java.util.Optional;

public interface CoursePurchaseHistoryRepository extends AbstractRepository<CoursePurchaseHistory, String> {
    Optional<CoursePurchaseHistory> findByUserIdAndCourseId(String sessionId, String id);
}

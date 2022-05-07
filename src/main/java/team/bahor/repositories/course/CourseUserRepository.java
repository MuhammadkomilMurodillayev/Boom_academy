package team.bahor.repositories.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.courses.CourseUser;

import java.util.Optional;

public interface CourseUserRepository extends JpaRepository<CourseUser, String> {

    @Query(value = "select * from boom_academy.main.course_user where course_id = ?1 and user_id = ?2 and status = 0", nativeQuery = true)
    CourseUser findByCourseIdAndUserIdAndStatus(String courseId, String userId);

    @Query(value = "select cu.* from boom_academy.main.course_user cu " +
            "inner join boom_academy.main.section s on cu.id = s.course_id " +
            "inner join boom_academy.main.lesson l on s.id = l.section_id " +
            "where l.id = ?1 and cu.user_id = ?2 and cu.is_deleted = 0 and  cu.status = 0", nativeQuery = true)
    Optional<CourseUser> findByLessonIdAndUserId(String lessonId, String userId);
}

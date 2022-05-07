package team.bahor.repositories.course;

import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.courses.Course;
import team.bahor.repositories.base.AbstractRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends AbstractRepository<Course, String> {

    @Query(value = "select * from boom_academy.main.courses where id = ?1 and  created_by = ?2 and is_deleted = 0 and status = 0", nativeQuery = true)
    Optional<Course> findByIdAndCreatedBy(String id, String createdBy);

    @Query(value = "select * from boom_academy.main.courses where id = ?1 and status = 0 and is_deleted = 0", nativeQuery = true)
    Optional<Course> activeThisCourse(String id);

    //Todo return "TEACHER RATING"
    @Query(value = "select sum(rating)/count(*) from boom_academy.main.courses where created_by = ?1 and is_deleted = 0", nativeQuery = true)
    float courseRatingActive(String id);

    @Query(value = "select created_by from boom_academy.main.courses where id = ?1 and is_deleted = 0", nativeQuery = true)
    String createdThisCourse(String courseId);

    List<Course> findAllByDeletedFalse();

    List<Course> findAllByStatusAndDeletedFalse(Short status);

    List<Course> findAllByCreatedByAndDeletedFalse(String userId);

    List<Course> findAllByCreatedByAndStatusAndDeletedFalse(String userId, Short status);

    Optional<Course> findByIdAndDeletedFalse(String id);

    Optional<Course> findByIdAndCreatedByAndDeletedFalse(String id, String createdBy);

    @Query(value = "select price from boom_academy.main.courses where id = ?1 and is_deleted = 0 and status = 0", nativeQuery = true)
    double getCourseAmount(String courseId);

}

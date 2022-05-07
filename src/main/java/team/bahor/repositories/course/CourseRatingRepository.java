package team.bahor.repositories.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.courses.CourseRating;
import team.bahor.repositories.base.BaseGenericRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CourseRatingRepository extends JpaRepository<CourseRating, String>, BaseGenericRepository {


    @Modifying
    @Query(value = "update boom_academy.main.course_rating set is_deleted = 1, updated_at = now() where id = ?1 and user_id = ?2", nativeQuery = true)
    void courseDeleteByCourseRating(String s, String id);

    @Query(value = "select * from boom_academy.main.course_rating where id = ?1 and  user_id = ?2 and  is_deleted = 0", nativeQuery = true)
    Optional<CourseRating> courseByIdAndUserId(String id, String userId);

    @Query(value = "select * from boom_academy.main.course_rating where id = ?1 and  is_deleted = 0", nativeQuery = true)
    Optional<CourseRating> courseById(String id);

//    @Query(value = "select ceil(sum(rating)/count(*)) from boom_academy.main.course_rating where course_id = ?1 and is_deleted = 0", nativeQuery = true)
//    byte courseRatingActive(String id);

    @Query(value = "select * from boom_academy.main.course_rating where course_id = ?1 and user_id = ?2 and  is_deleted = 0",nativeQuery = true)
    Optional<CourseRating> findByCourseIdAndUserId(String cId, String uId);

    @Query(value = "select * from boom_academy.main.course_rating where id = ?1 and  is_deleted = 0",nativeQuery = true)
    Optional<CourseRating> findByCourseIdAndUserId(String id);

    @Query(value = "select from boom_academy.main.course_rating where course_id = ?1 and is_deleted = 0", nativeQuery = true)
    List<CourseRating> findAllByCourseId(String cId);
}

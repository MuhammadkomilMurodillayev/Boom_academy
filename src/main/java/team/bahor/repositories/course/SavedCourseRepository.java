package team.bahor.repositories.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.courses.SavedCourse;
import team.bahor.repositories.base.BaseGenericRepository;

public interface SavedCourseRepository extends JpaRepository<SavedCourse, String>, BaseGenericRepository {


    @Query(value = "select * from boom_academy.main.saved_courses where user_id = ?1 and course_id = ?2 and is_deleted = 0 and  status = 0", nativeQuery = true)
    SavedCourse findByCourseIdAndUserId(String userId, String courseId);

    @Query(value = "select * from boom_academy.main.saved_courses where id = ?1  and is_deleted = 0", nativeQuery = true)
    SavedCourse findByIdNoDelete(String id);


    @Modifying
    @Query(value = "update boom_academy.main.saved_courses set is_deleted = 1, updated_at = now() where user_id = ?1 and course_id = ?2", nativeQuery = true)
    void deleteThisUserSavedCourse(String userId, String courseId);


    @Modifying
    @Query(value = "update boom_academy.main.saved_courses set is_deleted = 1, updated_at = now() where id = ?1 and is_deleted = 0", nativeQuery = true)
    void deleteByIdNoHard(String id);

    @Query(value = "select boom_academy.main.saved_course( ?1 )", nativeQuery = true)
    String getByCourseSavedDto(String id);

    @Query(value = "select boom_academy.main.saved_course_array( ?1 )", nativeQuery = true)
    String getByCoursesAllSavedDto(String id);


}

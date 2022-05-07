package team.bahor.repositories.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.courses.Course;
import team.bahor.entity.tag.Tags;
import team.bahor.repositories.base.BaseGenericRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TagsRepository extends JpaRepository<Tags, String>, BaseGenericRepository {
    @Query(value = "select * from boom_academy.main.tags where course_id = ?1 and name = ?2 and is_deleted = 0", nativeQuery = true)
    Tags equalsElements(String courseId, String name);

    @Transactional
    @Modifying
    @Query(value = "update boom_academy.main.tags set is_deleted = 1, updated_at = now() where id = ?1 and is_deleted = 0", nativeQuery = true)
    void deletedThisIdTag(String id);

    @Query(value = "select * from boom_academy.main.tags where id = ?1 and is_deleted = 0", nativeQuery = true)
    Optional<Tags> noDeletedTag(String id);

    @Query(value = "select * from boom_academy.main.tags where course_id = ?1 and is_deleted = 0", nativeQuery = true)
    List<Tags> findAllCourseTags(String courseId);

    @Query(value = "select c.* from boom_academy.main.tags t inner join boom_academy.main.courses c on t.course_id = c.id " +
            "where t.name = ?1 and t.is_deleted = 0 and c.is_deleted = 0 and c.status = 0", nativeQuery = true)
    List<Course> findAllTagsCourses(String name);
}

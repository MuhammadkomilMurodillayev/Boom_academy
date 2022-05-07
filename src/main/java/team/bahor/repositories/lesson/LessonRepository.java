package team.bahor.repositories.lesson;

import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.lesson.Lesson;
import team.bahor.repositories.base.AbstractRepository;

import java.util.Optional;

public interface LessonRepository extends AbstractRepository<Lesson, String> {

    @Query(value = "select * from boom_academy.main.lesson where id = ?1 and is_deleted = 0 and status = 0", nativeQuery = true)
    Optional<Lesson> findByLessonId(String id);


    @Query(value = "select l.* from boom_academy.main.lesson l " +
            "inner join boom_academy.main.section s on l.section_id = s.id " +
            "inner join boom_academy.main.courses c on c.id = s.course_id " +
            "inner join boom_academy.main.course_user cu on c.id = cu.course_id " +
            "where l.id = ?1 and cu.user_id = ?2 and l.is_deleted = 0 and s.is_deleted = 0 and c.is_deleted = 0 and cu.is_deleted = 0", nativeQuery = true)
    Optional<Lesson> takeThisCourse(String id, String userId);
}

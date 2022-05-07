package team.bahor.repositories.lesson;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.lesson.LessonQuestion;
import team.bahor.repositories.base.AbstractRepository;

import java.util.List;
import java.util.Optional;

public interface LessonQuestionRepository extends AbstractRepository<LessonQuestion, String> {

    @Modifying
    @Query(value = "update boom_academy.main.lesson_question set is_deleted = 1, updated_at = now() where id = ?1 and is_deleted = 0", nativeQuery = true)
    void deletedByThisId(String id);

    @Query(value = "select * from boom_academy.main.lesson_question where id =?1 and is_deleted = 0 and created_by = ?2", nativeQuery = true)
    Optional<LessonQuestion> findByQuestionId(String id, String createBy);

    @Query(value = "select * from boom_academy.main.lesson_question where id =?1 and is_deleted = 0", nativeQuery = true)
    Optional<LessonQuestion> findByQuestionId(String id);

    @Query(value = "select * from boom_academy.main.lesson_question where lesson_id = ?1 and  is_deleted = 0", nativeQuery = true)
    List<LessonQuestion> findByLessonIdAllQuestions(String lessonId);

    @Modifying
    @Query(value = "update boom_academy.main.lesson_question set like_count = like_count + 1, updated_at = now() where id = ?1 and is_deleted = 0", nativeQuery = true)
    void updateLikeCount(String id);

}

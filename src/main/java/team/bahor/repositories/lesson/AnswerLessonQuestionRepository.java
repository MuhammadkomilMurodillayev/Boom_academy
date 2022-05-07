package team.bahor.repositories.lesson;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.lesson.AnswerLessonQuestion;
import team.bahor.repositories.base.AbstractRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerLessonQuestionRepository extends AbstractRepository<AnswerLessonQuestion, String> {
    @Query(value = "select * from boom_academy.main.answer_lesson_question where lesson_question_id = ?1 and is_deleted = 0", nativeQuery = true)
    List<AnswerLessonQuestion> findAllByQuestionId(String questionId);

    @Modifying
    @Query(value = "update boom_academy.main.answer_lesson_question set is_deleted = 1, updated_at = now() where id = ?1 and is_deleted = 0", nativeQuery = true)
    void deletedById(String id);

    @Query(value = "select * from boom_academy.main.answer_lesson_question where id = ?1 and is_deleted = 0", nativeQuery = true)
    Optional<AnswerLessonQuestion> findByAnswerId(String id);

    @Query(value = "select * from boom_academy.main.answer_lesson_question where id = ?1 and created_by = ?2 and is_deleted = 0", nativeQuery = true)
    Optional<AnswerLessonQuestion> findByAnswerId(String id, String userId);

    @Modifying
    @Query(value = "update boom_academy.main.answer_lesson_question set like_count = like_count + 1, updated_at = now() where id = ?1 and is_deleted = 0", nativeQuery = true)
    void updateLikeCount(String id);
}

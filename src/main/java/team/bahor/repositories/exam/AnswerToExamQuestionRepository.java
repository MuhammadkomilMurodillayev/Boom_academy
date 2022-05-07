package team.bahor.repositories.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.exam.AnswerToExamQuestion;
import team.bahor.entity.exam.ExamQuestion;
import team.bahor.repositories.base.BaseGenericRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AnswerToExamQuestionRepository extends JpaRepository<AnswerToExamQuestion, String>, BaseGenericRepository {
    @Transactional
    @Modifying
    @Query(value = "update AnswerToExamQuestion at set at.deleted = true where at.id = ?1")
    void deleted(String id);

    AnswerToExamQuestion getByIdAndDeletedIsFalse(String id);

    List<AnswerToExamQuestion> getByDeletedFalse();

    List<AnswerToExamQuestion> getByExamQuestionIdAndDeletedFalse(String id);

    @Query(value = "select exists(select *\n" +
            "              from main.exam_question eq\n" +
            "                       inner join main.answer_to_exam_question ateq on eq.id = ateq.exam_question_id\n" +
            "                       inner join main.exam e on eq.exam_id = e.id\n" +
            "                       inner join main.courses c on c.id = e.course_id\n" +
            "              where c.created_by = ?2\n" +
            "                and ateq.id = ?1)", nativeQuery = true)
    boolean isTeacher(String questionId, String sessionId);

}

package team.bahor.repositories.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.exam.ExamQuestion;
import team.bahor.repositories.base.BaseGenericRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, String>, BaseGenericRepository {

    @Transactional
    @Modifying
    @Query(value = "update ExamQuestion eq set eq.deleted = true where eq.id = ?1")
    void deleted(String id);

    @Query("select e from ExamQuestion e where e.id = ?1 and e.deleted = false")
    ExamQuestion getByIdAndDeletedIsFalse(String id);

    List<ExamQuestion> getByDeletedFalse();

    @Transactional
    @Modifying
    @Query(value = "update ExamQuestion eq set eq.status = 400 where eq.id = ?1")
    void block(String id);

    @Query("select e from ExamQuestion e where e.examId = ?1 and e.deleted = false and e.status = 0")
    List<ExamQuestion> getAllByExamIdAndDeletedFalseAndStatus(String id);

    @Query(value = "select eq.id from boom_academy.main.exam_question eq where eq.is_deleted = 0 and eq.exam_id = ?1 and cast(eq.mark as varchar) = ?2 order by random() limit ?3", nativeQuery = true)
    List<String> getByExamId(String examId, String mark, Integer count);


    @Query(value = "select eq.mark, count(*) from boom_academy.main.exam_question eq where eq.is_deleted = 0 and eq.exam_id = ?1 group by eq.mark", nativeQuery = true)
    List<Object[]> informationForCreateExamUser(String examId);

    @Query(value = "select eq.* from boom_academy.main.exam_question eq inner join boom_academy.main.exam_question_user equ on eq.id = equ.exam_question_id where equ.exam_user_id = ?1 and equ.order_question = ?2", nativeQuery = true)
    ExamQuestion getByExamUserIdAndOrder(String examUserId, Integer nextOrder);

    @Query(value = "select exists(select e.id from main.exam e inner join main.courses c on e.course_id = c.id where e.id = ?1 and c.created_by = ?2)",nativeQuery = true)
    boolean isTeacher(String examId, String sessionId);


}

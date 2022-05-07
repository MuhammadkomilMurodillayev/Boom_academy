package team.bahor.repositories.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.exam.ExamQuestionUser;

import javax.transaction.Transactional;

public interface ExamQuestionUserRepository extends JpaRepository<ExamQuestionUser, String> {
    @Transactional
    @Modifying
    @Query(value = "update boom_academy.main.exam_question_user equ set marked_answer_id = ?3 where equ.exam_user_id = ?1 and equ.order_question = ?2", nativeQuery = true)
    void saveExamSolveDto(String examUserId, Integer order, String markedAnswerId);
}

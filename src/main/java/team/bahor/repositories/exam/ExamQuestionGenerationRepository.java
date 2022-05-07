package team.bahor.repositories.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.bahor.dto.exam.examQuestionGeneration.ExamQuestionGenerationUpdateDto;
import team.bahor.entity.exam.ExamQuestionGeneration;
import team.bahor.repositories.base.BaseGenericRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface ExamQuestionGenerationRepository extends JpaRepository<ExamQuestionGeneration, String>, BaseGenericRepository {
    List<ExamQuestionGeneration> findAllByDeletedFalseAndExamId(String id);


    @Query(value = "update main.exam_question_generation set count = ?3 where exam_id = ?1 and mark = ?2}", nativeQuery = true)
    void update(String id, String k, Integer v);

}

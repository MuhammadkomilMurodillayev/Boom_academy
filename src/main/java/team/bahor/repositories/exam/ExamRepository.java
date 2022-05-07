package team.bahor.repositories.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.bahor.dto.exam.exam.Certificate;
import team.bahor.dto.exam.exam.ExamDto;
import team.bahor.entity.exam.Exam;
import team.bahor.repositories.base.BaseGenericRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, String>, BaseGenericRepository {

    Optional<Exam> findByIdAndDeletedFalse(String id);

    List<Exam> findAllByDeletedFalse();

    @Transactional
    @Modifying
    @Query(value = "update Exam set duration = :#{#examDto.duration},maxMark = :#{#examDto.maxMark},questionCount = :#{#examDto.questionCount},status = 301 where id= :#{#examDto.id}")
    void update(ExamDto examDto);

    @Transactional
    @Modifying
    @Query(value = "update Exam eq set eq.deleted = true where eq.id = ?1")
    void deleted(String id);

    @Query("select e from Exam e where e.id = ?1 and e.deleted = false")
    Exam getByIdAndDeletedIsFalse(String id);

    @Transactional
    @Modifying
    @Query(value = "update Exam eq set eq.status = 302 where  eq.id = ?1")
    void block(String id);

    @Query(value = "select e.duration from boom_academy.main.exam e where e.is_deleted = 0 and e.id = ?1", nativeQuery = true)
    Long getByIdForDuration(String examId);

    @Query(value = "select e.* from boom_academy.main.exam e inner join boom_academy.main.exam_user eu on e.id = eu.exam_id where eu.id = ?1", nativeQuery = true)
    Exam getByExamUserId(String examUserId);

    @Query(value = "select boom_academy.main.finish_exam( ?1 )", nativeQuery = true)
    String finish(String examUserId);

    @Query(value = "select  exists(select * from boom_academy.main.exam where is_deleted = 0 and course_id = ?1 )", nativeQuery = true)
    boolean isMakeExam(String courseId);

    @Query(value = "select exists(select  * from boom_academy.main.courses c where c.created_by = ?1 and id = ?2)", nativeQuery = true)
    boolean isCanCreateBegin(String sessionId, String courseId);

    @Query(value = "select exists(select e.id from boom_academy.main.exam e inner join boom_academy.main.courses c on e.course_id = c.id where e.id = ?2 and c.created_by = ?1)", nativeQuery = true)
    boolean isTeacher(String sessionId, String examId);

    @Query(value = "select exists(select * from  boom_academy.main.courses where id = ?1 and is_deleted = 0)", nativeQuery = true)
    boolean isThereCourse(String courseId);

    @Query(value = "select exists(select * from boom_academy.main.exam where is_deleted = 0 and id = ?1)", nativeQuery = true)
    boolean isThereExam(String examId);

    @Query(value = "select exists(select * from boom_academy.main.exam where is_deleted = 0 and id = ?1 and status = 0)", nativeQuery = true)
    boolean isThereExamAndNotBlockAndActive(String examId);

    @Query(value = "select exists(select * from boom_academy.main.course_user cu where cu.is_deleted = 0 and cu.user_id = ?1 and cu.status = 0)", nativeQuery = true)
    boolean isStudentOfCourse(String sessionId);

    @Query(value = "select exists(select * from boom_academy.main.course_user cu where cu.user_id = ?1 and cu.is_completed = 1)", nativeQuery = true)
    boolean isCompleted(String sessionId);

    @Query(value = "select exists(select  * from  boom_academy.main.exam_user where is_deleted = 0 and status = 0 and id = ?1)", nativeQuery = true)
    boolean isThereExamUser(String examUserId);

    @Query(value = "select (current_timestamp + interval '5 hour') > finishing_time from boom_academy.main.exam_user eu where eu.id = ?1", nativeQuery = true)
    boolean hasTime(String examUserId);

    @Query(value = "select max(equ.order_question) from boom_academy.main.exam_question_user equ where equ.exam_user_id = ?1", nativeQuery = true)
    Integer maxOrder(String examUserId);

    @Query(value = "select cast((select row_to_json(\"table\")  from (select au.full_name, c.name as course_name, eu.percentage from main.courses c inner join main.exam e on c.id = e.course_id inner join main.exam_user eu on e.id = eu.exam_id inner join main.auth_users au on au.id = eu.user_id where au.id = ?1 and c.id = ?2) \"table\") as text)", nativeQuery = true)
    String createData(String sessionId, String courseId);

    @Query(value = "select cast((select array_to_json(array_agg(row_to_json(\"table\"))) from (select t.id,t.name from main.tags t where t.is_deleted = 0 and t.name ilike ?1 union select c.id,c.name from  main.courses c where c.is_deleted = 0 and c.status = 0 and c.name ilike ?1) \"table\") as text)\n", nativeQuery = true)
    String search(String search);

    @Query(value = "select cast((select array_to_json(array_agg(row_to_json(\"table\")))\n" +
            "             from (select mc.name as course_name, count(*), sum(cph.payment_amount)\n" +
            "                   from main.course_purchase_history cph\n" +
            "                            inner join main.courses mc on cph.course_id = mc.id\n" +
            "                   group by mc.name) \"table\"\n" +
            ") as text)", nativeQuery = true)
    String accountant();
}

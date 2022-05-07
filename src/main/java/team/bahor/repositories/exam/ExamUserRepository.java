package team.bahor.repositories.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team.bahor.entity.exam.ExamUser;

import java.sql.Timestamp;

public interface ExamUserRepository extends JpaRepository<ExamUser, String> {
    @Query(value = "select eu.finishing_time from boom_academy.main.exam_user eu where eu.id = ?1", nativeQuery = true)
    Timestamp findByExamUserIdForFinishingTime(String examUserId);
}

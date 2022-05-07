package team.bahor.entity.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.bahor.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Bu entity userlar ishlagan quizlarni saqlab ketadi
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "quiz_user_status_index", columnList = "status"),
        @Index(name = "quiz_user_user_id_index", columnList = "userId"),
        @Index(name = "quiz_user_quiz_id_index", columnList = "quizId"),
        @Index(name = "quiz_user_finishing_time_index", columnList = "finishingTime")

})
public class QuizUser extends Auditable {
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String quizId;

    @Column(nullable = false)
    private Short mark;

    @Column(nullable = false)
    private Double percentage;

    private LocalDateTime finishingTime;

}

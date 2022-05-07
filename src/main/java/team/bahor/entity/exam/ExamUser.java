package team.bahor.entity.exam;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.bahor.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "exam_user_status_index", columnList = "status"),
        @Index(name = "exam_user_user_id_index", columnList = "userId"),
        @Index(name = "exam_user_exam_id_index", columnList = "examId")
})
public class ExamUser extends Auditable {
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String examId;

    private int mark;

    private double percentage;

    private LocalDateTime finishingTime;
}

package team.bahor.entity.exam;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.bahor.entity.base.Auditable;
import team.bahor.enums.types.QuestionType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "exam_question_status_index", columnList = "status"),
        @Index(name = "exam_question_exam_id_index", columnList = "examId"),
        @Index(name = "exam_question_mark_index", columnList = "mark"),
})
public class ExamQuestion extends Auditable {

    @Column(nullable = false)
    private String examId;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Column(nullable = false)
    private Integer mark;
}

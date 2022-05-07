package team.bahor.entity.exam;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import team.bahor.entity.base.BaseGenericEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "exam_question_generation_exam_id_index", columnList = "exam_id"),
})
public class ExamQuestionGeneration implements BaseGenericEntity {
    @Id
    @Column(unique = true)
    private String id;

    @Column(name = "exam_id", nullable = false)
    private String examId;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private Integer mark;

    @Column(name = "is_deleted", columnDefinition = "NUMERIC default 0")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean deleted;
}

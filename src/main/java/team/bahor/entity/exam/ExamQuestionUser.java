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
        @Index(name = "exam_question_user_exam_id_index", columnList = "examUserId"),
        @Index(name = "exam_question_user_exam_question_id_index", columnList = "examQuestionId"),
})
public class ExamQuestionUser implements BaseGenericEntity {
    @Id
    @Column(unique = true)
    private String id;

    @Column(nullable = false)
    private String examUserId;

    private Integer orderQuestion;

    @Column(columnDefinition = "NUMERIC default 0")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean correctlySolved;

    @Column(nullable = false)
    private String examQuestionId;

    private String markedAnswerId;
}

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
@NoArgsConstructor@Table(indexes = {
        @Index(name = "answer_to_exam_question_exam_question_id_index", columnList = "examQuestionId")
})
public class AnswerToExamQuestion implements BaseGenericEntity {
    @Id
    @Column(unique = true)
    private String id;

    @Column(nullable = false)
    private String examQuestionId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "NUMERIC default 0")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean correct;//true bolsa togri javob boladi

    @Column(name = "is_deleted",columnDefinition = "NUMERIC default 0")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean deleted;
}

package team.bahor.entity.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "answer_to_quiz_question_quiz_question_id_index", columnList = "quizQuestionId"),
})
public class AnswerToQuizQuestion {
    @Id
    @Column(unique = true)
    private String id;

    @Column(nullable = false)
    private String quizQuestionId;

    @Column(nullable = false)
    private String title;

    @Column(name = "is_correct", columnDefinition = "NUMERIC default 0")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean correct;
}

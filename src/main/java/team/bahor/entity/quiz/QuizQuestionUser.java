package team.bahor.entity.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "quiz_question_user_quiz_id_index", columnList = "quizId"),
        @Index(name = "quiz_question_user_quiz_question_id_index", columnList = "quizQuestionId")
})
public class QuizQuestionUser {
    @Id
    @Column(unique = true)
    private String id;

    @Column(nullable = false)
    private String quizId; // -> this is id of QuizUser table

    @Column(columnDefinition = "NUMERIC default 0")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean correctlySolved;

    @Column(nullable = false)
    private String quizQuestionId;
}

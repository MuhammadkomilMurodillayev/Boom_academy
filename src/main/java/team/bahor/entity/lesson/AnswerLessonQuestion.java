package team.bahor.entity.lesson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.bahor.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "answer_lesson_question_status_index", columnList = "status"),
        @Index(name = "answer_lesson_question_lesson_question_id_index", columnList = "lesson_question_id"),
        @Index(name = "answer_lesson_question_created_by_index", columnList = "created_by"),
})
public class AnswerLessonQuestion extends Auditable {
    @Column(name = "lesson_question_id", nullable = false)
    private String lessonQuestionId;

    @Column(nullable = false)
    private String title;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(columnDefinition = "integer default 0")
    private Integer likeCount;
}

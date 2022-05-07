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
        @Index(name = "lesson_question_status_index", columnList = "status"),
        @Index(name = "lesson_question_lesson_id_index", columnList = "lessonId"),
        @Index(name = "lesson_question_created_by_index", columnList = "createdBy")
})
public class LessonQuestion extends Auditable {
    @Column(nullable = false)
    private String lessonId;

    @Column(nullable =false)
    private String title;

    @Column(nullable = false)
    private String createdBy;

    @Column(columnDefinition = "integer default 0")
    private Integer likeCount;
}

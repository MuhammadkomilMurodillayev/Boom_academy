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
        @Index(name = "notes_to_lessons_status_index", columnList = "status"),
        @Index(name = "notes_to_lessons_created_by_index", columnList = "created_by"),
        @Index(name = "notes_to_lessons_lesson_id_index", columnList = "lessonId"),
})
public class NotesToLessons extends Auditable {

    @Column(name = "created_by",nullable = false)
    private String createdBy;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String lessonId;
}

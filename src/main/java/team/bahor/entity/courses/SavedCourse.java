package team.bahor.entity.courses;

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
@Table(name = "saved_courses", indexes = {
        @Index(name = "saved_course_status_index", columnList = "status"),
        @Index(name = "saved_course_course_id_index", columnList = "courseId"),
        @Index(name = "saved_course_user_id_index", columnList = "userId"),
})
public class SavedCourse extends Auditable {

    @Column(nullable = false)
    private String courseId;

    @Column(nullable = false)
    private String userId;

}

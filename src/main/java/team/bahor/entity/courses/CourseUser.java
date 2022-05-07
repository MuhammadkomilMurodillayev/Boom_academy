package team.bahor.entity.courses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import team.bahor.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "course_user_status_index", columnList = "status"),
        @Index(name = "course_user_user_id_index", columnList = "userId"),
        @Index(name = "course_user_course_id_index", columnList = "courseId"),
        @Index(name = "course_user_started_at_index", columnList = "startedAt")
})
public class CourseUser extends Auditable {
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String courseId;

    @Column(name = "is_started", columnDefinition = "NUMERIC default 0")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean started;

    private LocalDateTime startedAt;

    @Column(name = "is_completed", columnDefinition = "NUMERIC default 0")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean completed;
}

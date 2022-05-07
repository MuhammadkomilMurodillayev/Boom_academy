package team.bahor.entity.certificate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.bahor.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "certificate_course_id_index", columnList = "courseId"),
        @Index(name = "certificate_user_id_index", columnList = "userId"),
        @Index(name = "certificate_status_index", columnList = "status")
})
public class Certificate extends Auditable {
    @Column(nullable = false)
    private String courseId;

    @Column(nullable = false)
    private String userId;
}

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
@Table(indexes = {
        @Index(name = "section_status_index", columnList = "status"),
        @Index(name = "section_course_id_index", columnList = "course_id"),
        @Index(name = "section_position_index", columnList = "position"),
        @Index(name = "section_status_index", columnList = "status"),
})
public class Section extends Auditable {

    @Column(nullable = false, name = "course_id")
    private String courseId;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private Short position;

    @Column(nullable = false)
    private String createdBy;

//    @Column(name = "is_completed", columnDefinition = "NUMERIC default 0")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
//    private boolean completed;
}

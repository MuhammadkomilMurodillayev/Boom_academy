package team.bahor.entity.exam;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;
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
        @Index(name = "exam_title_index", columnList = "title"),
        @Index(name = "exam_course_id_index", columnList = "course_id"),
        @Index(name = "exam_status_index", columnList = "status")
})
public class Exam extends Auditable {
    @Column(name = "course_id", nullable = false)
    private String courseId;

    @Column(columnDefinition = "int8 default 0")
    private Long duration; //milliseconds

    @Column(columnDefinition = "int2 default 0")
    private Integer questionCount;

    @Value("int2 0")
    @Column(columnDefinition = "int2 default 0")
    private Integer maxMark;

    @Column(nullable = false)
    private String title;

    private String description;
}

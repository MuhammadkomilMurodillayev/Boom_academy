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
        @Index(name = "course_rating_status_index", columnList = "status"),
        @Index(name = "course_rating_course_id_index", columnList = "courseId"),
        @Index(name = "course_rating_user_id_index", columnList = "userId"),
        @Index(name = "course_rating_rating_index", columnList = "rating")
})
public class CourseRating extends Auditable {
    @Column(nullable = false)
    private String courseId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, columnDefinition = "float4 default 0")
    private float rating;

    private String body; // -> course haqida fikr qoldirmoqchi bo'lsa body'da berib yuboriladi
}

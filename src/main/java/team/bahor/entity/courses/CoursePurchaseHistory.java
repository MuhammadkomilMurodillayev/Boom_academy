package team.bahor.entity.courses;

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
        @Index(name = "course_project_history_status_index", columnList = "status"),
        @Index(name = "course_project_history_course_id_index", columnList = "courseId"),
        @Index(name = "course_project_history_user_id_index", columnList = "userId"),
        @Index(name = "course_project_history_payment_amount_index", columnList = "paymentAmount"),
        @Index(name = "course_project_history_coupon_id_index", columnList = "couponId"),
})
public class CoursePurchaseHistory extends Auditable {
    @Column(nullable = false)
    private String courseId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private Double paymentAmount;

    private String couponId;

    private Double discount; // -> discount will be given in percentages, if this field is null or equals to zero then there is no discount
}

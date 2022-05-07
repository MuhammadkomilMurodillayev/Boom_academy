package team.bahor.entity.finance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.bahor.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "coupon_status_index", columnList = "status"),
        @Index(name = "coupon_course_id_index", columnList = "courseId"),
        @Index(name = "coupon_code_index", columnList = "code")
})
public class Coupon extends Auditable {

    @Column(nullable = false)
    private String courseId;

    @Column(nullable = false)
    private String code;

    private LocalDate dueDate;

    private int limitSize;

    private double discountAmount;

    private double discountPercentage;

}


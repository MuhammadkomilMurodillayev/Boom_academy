package team.bahor.dto.course.purchase;


import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

import java.time.LocalDateTime;

@Getter
@Setter
public class CoursePurchaseDto extends GenericDto {
    private String courseId;

    private String userId;

    private Double paymentAmount;

    private String couponId;

    private Double discount;

    private LocalDateTime createdAt;

}

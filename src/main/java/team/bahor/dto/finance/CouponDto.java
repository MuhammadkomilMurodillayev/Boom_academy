package team.bahor.dto.finance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import team.bahor.dto.GenericDto;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class CouponDto extends GenericDto {

    private String courseId;

    private String code;

    private int limitSize;

    private double discountAmount;

    private double discountPercentage;

    private LocalDate dueDate;

}

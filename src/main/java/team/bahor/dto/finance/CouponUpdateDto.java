package team.bahor.dto.finance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;
import team.bahor.dto.GenericDto;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class CouponUpdateDto extends GenericDto {

    @Size(max = 12, min = 12, message = "min size code length must be 12")
    private String code;

    @Range(min = 1, message = "limit size must be minimum {min}")
    private int limitSize;

    @Range(min = 0, message = "discount amount must be minimum {min}")
    private double discountAmount;

    @Range(min = 0, max = 100, message = "discount percentage must be between {min}% - {max}%")
    private double discountPercentage;

    private LocalDate dueDate;

}

package team.bahor.dto.finance;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import team.bahor.dto.BaseGenericDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class CouponCreateDto implements BaseGenericDto {

    @NotNull(message = "bad request")
    private String courseId;

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

package team.bahor.dto.course.purchase;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CoursePurchaseUpdateDto extends GenericDto {
    @NotNull(message = "courseId is null")
    private String courseId;

    @NotNull(message = "userId is null")
    private String userId;

    private String couponCode;

}

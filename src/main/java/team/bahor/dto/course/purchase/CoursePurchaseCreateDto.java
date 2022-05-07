package team.bahor.dto.course.purchase;


import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CoursePurchaseCreateDto implements BaseGenericDto {

    @NotNull(message = "courseId is null")
    private String courseId;

    @NotNull(message = "userId is null")
    private String userId;

    private String couponCode;

}

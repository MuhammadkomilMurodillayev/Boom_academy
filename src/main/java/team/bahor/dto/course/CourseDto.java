package team.bahor.dto.course;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDto extends GenericDto {

    @Pattern(regexp = "\\w")
    private String name;

    private String description;

    private Integer purchaseCount;

    @NotBlank
    private Double price;

    @NotBlank
    private String category;

    private Short duration;

    private Integer rating;

    private short status;

    private Integer ratingCount;

}

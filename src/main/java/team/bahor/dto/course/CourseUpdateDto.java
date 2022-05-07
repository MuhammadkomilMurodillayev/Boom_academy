package team.bahor.dto.course;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;
import team.bahor.enums.CourseCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CourseUpdateDto extends GenericDto {

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private Double price;

    @NotBlank
    private String category;

    private Short duration;

}

package team.bahor.dto.course;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;
import team.bahor.dto.BaseGenericDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@Builder
@ParameterObject
public class CourseCreateDto implements BaseGenericDto {

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private Double price;

    @NotBlank
    private String category;

    private Short duration;

}

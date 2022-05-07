package team.bahor.dto.tag;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TagUpdateDto extends GenericDto {
    @NotBlank(message = "name is null")
    private String name;

    @NotBlank(message = "courseId is null")
    private String courseId;
}

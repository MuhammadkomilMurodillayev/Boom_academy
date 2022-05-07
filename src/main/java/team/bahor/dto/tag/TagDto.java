package team.bahor.dto.tag;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TagDto extends GenericDto {
    @NotBlank
    private String name;

    @NotBlank
    private String courseId;

}

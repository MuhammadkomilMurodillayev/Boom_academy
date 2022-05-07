package team.bahor.dto.course.section;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SectionPositionUpdateDto extends GenericDto {
    @NotNull
    private Short position;
}

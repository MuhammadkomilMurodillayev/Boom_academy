package team.bahor.dto.course.section;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SectionDto extends GenericDto {

    @NotNull(message ="courseId is null")
    private String courseId;

    @NotNull(message ="title is null")
    private String title;

    @NotNull(message ="description is null")
    private String description;

    @NotNull(message ="position is null")
    private Short position;

    @NotNull(message ="createdBy is null")
    private String createdBy;

    @NotNull(message ="status is null")
    private Short status;

}

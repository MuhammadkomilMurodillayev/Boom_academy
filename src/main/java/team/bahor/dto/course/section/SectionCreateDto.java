package team.bahor.dto.course.section;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SectionCreateDto implements BaseGenericDto {
    @NotNull
    private String courseId;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Short position;

    @NotNull
    private String createdBy;
}

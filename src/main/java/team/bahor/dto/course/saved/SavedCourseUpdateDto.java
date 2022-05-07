package team.bahor.dto.course.saved;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SavedCourseUpdateDto extends GenericDto {
    @NotNull
    private String courseId;

    @NotNull
    private String userId;
}

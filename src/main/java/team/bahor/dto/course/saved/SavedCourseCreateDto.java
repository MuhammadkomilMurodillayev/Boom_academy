package team.bahor.dto.course.saved;


import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SavedCourseCreateDto implements BaseGenericDto {
    @NotNull
    private String courseId;

    @NotNull
    private String userId;
}

package team.bahor.dto.course.rating;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CourseRatingCreateDto implements BaseGenericDto {

    @NotNull
    private String courseId;

    @NotNull
    private String userId;

    @NotNull
    private float rating;

    private String body;
}

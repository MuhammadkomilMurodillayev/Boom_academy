package team.bahor.dto.course.rating;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CourseRatingUpdateDto extends GenericDto {

    @NotNull
    private float rating;

    private String body;
}

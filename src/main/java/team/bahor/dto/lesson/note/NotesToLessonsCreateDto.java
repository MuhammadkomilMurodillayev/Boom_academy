package team.bahor.dto.lesson.note;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class NotesToLessonsCreateDto implements BaseGenericDto {

    private String createdBy;

    @NotBlank(message = "title is null")
    private String title;

    @NotBlank(message = "lessonId is null")
    private String lessonId;
}

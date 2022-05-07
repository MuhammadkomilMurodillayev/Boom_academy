package team.bahor.dto.lesson.question;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LessonQuestionCreateDto implements BaseGenericDto {
    @NotBlank(message = "lessonId is null")
    private String lessonId;

    @NotBlank(message = "title is null")
    private String title;

    private String createdBy;
}
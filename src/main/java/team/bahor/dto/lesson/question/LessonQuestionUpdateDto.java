package team.bahor.dto.lesson.question;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LessonQuestionUpdateDto extends GenericDto {

    @NotBlank(message = "title is null")
    private String title;
}

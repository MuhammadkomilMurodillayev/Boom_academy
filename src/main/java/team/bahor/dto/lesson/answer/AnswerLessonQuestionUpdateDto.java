package team.bahor.dto.lesson.answer;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AnswerLessonQuestionUpdateDto extends GenericDto {
    @NotBlank(message = "title is null")
    private String title;
}

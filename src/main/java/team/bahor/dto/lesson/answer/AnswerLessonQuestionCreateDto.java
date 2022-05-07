package team.bahor.dto.lesson.answer;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AnswerLessonQuestionCreateDto implements BaseGenericDto {
    @NotBlank(message = "lessonQuestionId is null")
    private String lessonQuestionId;

    @NotBlank(message = "title is null")
    private String title;

    private String createdBy;

}
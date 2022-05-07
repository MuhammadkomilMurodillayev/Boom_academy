package team.bahor.dto.lesson.answer;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;
import team.bahor.dto.user.CreateUserDto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AnswerLessonQuestionDto extends GenericDto {
    @NotBlank(message = "lessonQuestionId is null")
    private String lessonQuestionId;

    @NotBlank(message = "title is null")
    private String title;

    private String createdBy;

    private Integer likeCount;
}

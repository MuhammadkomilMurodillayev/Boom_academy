package team.bahor.dto.lesson.question;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionDto;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class LessonQuestionDto extends GenericDto {
    @NotBlank(message = "lessonId is null")
    private String lessonId;

    @NotBlank(message = "title is null")
    private String title;

    private String createdBy;

    private Integer likeCount;

    private List<AnswerLessonQuestionDto> answerLessonQuestionDtoList;
}

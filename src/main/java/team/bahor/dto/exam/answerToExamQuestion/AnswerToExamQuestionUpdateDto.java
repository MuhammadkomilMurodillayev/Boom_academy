package team.bahor.dto.exam.answerToExamQuestion;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

@Setter
@Getter
public class AnswerToExamQuestionUpdateDto extends GenericDto {

    private String title;

    private Boolean correct;
}

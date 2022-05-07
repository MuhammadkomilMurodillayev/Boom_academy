package team.bahor.dto.exam.answerToExamQuestion;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AnswerToExamQuestionDto extends GenericDto {
    private String examQuestionId;

    private String title;

    private boolean correct;
}

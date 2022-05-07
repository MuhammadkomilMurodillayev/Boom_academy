package team.bahor.dto.exam.examQuestion;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionDto;
import team.bahor.entity.base.BaseGenericEntity;
import team.bahor.enums.types.QuestionType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExamQuestionDtoForFinish implements BaseGenericDto {
    private QuestionType type;

    private Integer mark;

    private String title;

    private String userMarkAnswerTitle;

    private boolean correctlySolved;

    private List<AnswerToExamQuestionDto> answers = new ArrayList<>();

}

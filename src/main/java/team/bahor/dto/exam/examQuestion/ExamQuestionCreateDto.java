package team.bahor.dto.exam.examQuestion;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionCreateDto;
import team.bahor.enums.types.QuestionType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExamQuestionCreateDto implements BaseGenericDto {
    private String examId;

    private String title;

    private String type;

    private Integer mark;

    private List<AnswerToExamQuestionCreateDto> answers=new ArrayList<>();
}

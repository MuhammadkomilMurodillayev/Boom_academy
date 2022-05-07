package team.bahor.dto.exam.examQuestion;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionCreateDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExamQuestionUpdateDto extends GenericDto {
    private String title;

    private Short mark;
}

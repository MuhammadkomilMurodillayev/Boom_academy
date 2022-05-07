package team.bahor.dto.exam.examQuestionGeneration;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionCreateDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExamQuestionGenerationDto extends GenericDto {
    private String examId;

    private Integer count;

    private String mark;

}

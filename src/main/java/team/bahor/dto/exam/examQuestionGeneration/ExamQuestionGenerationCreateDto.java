package team.bahor.dto.exam.examQuestionGeneration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionCreateDto;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExamQuestionGenerationCreateDto implements BaseGenericDto {
    private String examId;

    private Integer count;

    private Integer mark;

}

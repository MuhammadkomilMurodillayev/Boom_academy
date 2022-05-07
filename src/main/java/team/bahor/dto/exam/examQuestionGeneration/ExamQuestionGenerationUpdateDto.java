package team.bahor.dto.exam.examQuestionGeneration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;
import team.bahor.dto.GenericDto;

@Getter
@Setter
@AllArgsConstructor
public class ExamQuestionGenerationUpdateDto extends GenericDto {
    private String examId;

    private String mark;

    private Integer count;
}

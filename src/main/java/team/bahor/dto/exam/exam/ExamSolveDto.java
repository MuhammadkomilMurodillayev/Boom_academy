package team.bahor.dto.exam.exam;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;
import team.bahor.dto.GenericDto;
import team.bahor.dto.exam.examQuestion.ExamQuestionDto;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExamSolveDto implements BaseGenericDto {
    private String examUserId;

    private String courseTitle;

    private String examTitle;

    private Integer order;

    private LocalDateTime finishingTime;

    private ExamQuestionDto questionDto;

    private String markedAnswerId;

    private Integer nextOrder;
}

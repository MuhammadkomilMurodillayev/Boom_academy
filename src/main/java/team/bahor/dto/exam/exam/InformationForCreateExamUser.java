package team.bahor.dto.exam.exam;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;
import team.bahor.dto.exam.examQuestion.ExamQuestionDto;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class InformationForCreateExamUser implements BaseGenericDto {
    private String examId;

    private String courseTitle;

    private String examTitle;

    private Map<String, Integer> questionCounts;
}

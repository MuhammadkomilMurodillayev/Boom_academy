package team.bahor.dto.exam.exam;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapping;
import team.bahor.dto.BaseGenericDto;
import team.bahor.dto.exam.examQuestion.ExamQuestionDto;
import team.bahor.dto.exam.examQuestion.ExamQuestionDtoForFinish;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class  FinishDto implements BaseGenericDto {
    private String courseId;

    private String courseTitle;

    private String examId;

    private String examTitle;

    private Integer questionCount;

    private Integer maxMark;

    private double percentage;

    List<ExamQuestionDtoForFinish> examQuestionDtos = new ArrayList<>();

}

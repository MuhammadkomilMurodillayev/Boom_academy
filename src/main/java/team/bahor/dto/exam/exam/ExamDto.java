package team.bahor.dto.exam.exam;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

import java.util.Map;

@Getter
@Setter
public class ExamDto extends GenericDto {
    private String courseId;

    private String courseTitle;

    private String title;

    private Long duration; //milliseconds

    private Integer questionCount;

    private Integer maxMark;

    private String description;

    private Map<String, Integer> questionCounts;
//todo shu yerga question list qoyib qolgan joylarni togirla
}

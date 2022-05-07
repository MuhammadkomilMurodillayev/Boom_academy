package team.bahor.dto.exam.exam;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

import java.util.Map;
@Getter
@Setter

public class ExamUpdateDto extends GenericDto {
    private Long duration; //milliseconds

    private String title;

    private String description;

    private Map<String, Integer> questionCounts;
}


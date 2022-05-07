package team.bahor.dto.exam.exam;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;
@Getter
@Setter
public class ExamCreateDtoBegin implements BaseGenericDto {
    private String courseId;

    private String title;

    private String description;

}

package team.bahor.dto.lesson;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LessonCreateDto implements BaseGenericDto {
    private String title;

    private String sectionId;

    private String position;

    private String content;
}

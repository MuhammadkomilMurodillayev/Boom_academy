package team.bahor.dto.course.section;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

@Getter
@Setter
public class SectionUpdateDto extends GenericDto {

    private String title;

    private String description;

    private Short status;
}
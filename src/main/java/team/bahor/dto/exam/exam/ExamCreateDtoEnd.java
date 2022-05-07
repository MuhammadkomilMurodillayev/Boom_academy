package team.bahor.dto.exam.exam;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Getter
@Setter
public class   ExamCreateDtoEnd extends GenericDto {
    @NotNull
    private Long duration; //milliseconds
    @NotNull
    private Map<String, Integer> questionCounts;//1-element questionning mark 2-element questionning count
}

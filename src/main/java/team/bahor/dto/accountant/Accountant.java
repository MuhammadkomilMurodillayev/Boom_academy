package team.bahor.dto.accountant;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import team.bahor.dto.BaseGenericDto;


@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Accountant implements BaseGenericDto {
    private String courseName;

    private int count;

    private double sum;
}

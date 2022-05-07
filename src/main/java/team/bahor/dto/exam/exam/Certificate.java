package team.bahor.dto.exam.exam;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import team.bahor.dto.BaseGenericDto;
import team.bahor.dto.GenericDto;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Certificate extends GenericDto {
    private String fullName;

    private String courseName;

    private double percentage;

    private Timestamp date;

    private String imzo;
}

package team.bahor.dto.course.saved;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import team.bahor.dto.GenericDto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SavedCourseDto extends GenericDto {

    private String id;

    private String courseId;


    private String userId;

    private String name;

    private Integer purchaseCount;

    private String username;

}

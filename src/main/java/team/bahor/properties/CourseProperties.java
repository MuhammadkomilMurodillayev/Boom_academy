package team.bahor.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "course")
@Getter
@Setter
public class CourseProperties {

    private Short activeStatus;
    private Short nonActiveStatus;

}

package team.bahor.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api.info")
@Getter
@Setter
public class OpenApiProperty {

    private String title;
    private String description;
    private String version;
    private String termOfService;
    private String contactName;
    private String contactEmail;
    private String contactUrl;
    private String licenseName;
    private String licenseUrl;

}

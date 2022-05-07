package team.bahor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import team.bahor.properties.OpenApiProperty;

import java.util.List;
import java.util.Properties;

@Configuration
@ConditionalOnProperty(name = "springdoc.swagger-ui.enabled", havingValue = "true", matchIfMissing = true)
public class    OpenApiConfigurer {

    private static final String BEARER_FORMAT = "JWT";
    private static final String SCHEME = "Bearer";
    private static final String SECURITY_SCHEME = "Security Scheme";
    private final OpenApiProperty openApiProperty;

    public OpenApiConfigurer(OpenApiProperty openApiProperty) {
        this.openApiProperty = openApiProperty;
    }

    @Bean
    public OpenAPI api() {

        return new OpenAPI()
                .schemaRequirement(SECURITY_SCHEME, getSecurityScheme())
                .security(getSecurityRequirement())
                .info(info());

    }
    private List<SecurityRequirement> getSecurityRequirement() {
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList(SECURITY_SCHEME);
        return List.of(securityRequirement);
    }

    private SecurityScheme getSecurityScheme() {
        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.bearerFormat(BEARER_FORMAT)
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .scheme(SCHEME);
        return securityScheme;
    }

    private Info info() {
        return new Info()
                .title(openApiProperty.getTitle())
                .version(openApiProperty.getVersion())
                .termsOfService(openApiProperty.getTermOfService())
                .description(openApiProperty.getDescription())
                .contact(new Contact()
                        .name(openApiProperty.getContactName())
                        .email(openApiProperty.getContactEmail())
                        .url(openApiProperty.getContactUrl())
                )
                .license(new License()
                        .name(openApiProperty.getLicenseName())
                        .url(openApiProperty.getLicenseUrl())
                );
    }
    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("dilshooodbek99akhmedov76@gmail.com");
        mailSender.setPassword("@Qwertyu77");
        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        return mailSender;
    }

}

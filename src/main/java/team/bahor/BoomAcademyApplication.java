package team.bahor;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.bahor.entity.user.AuthUser;
import team.bahor.enums.Role;
import team.bahor.properties.OpenApiProperty;
import team.bahor.properties.ServerProperties;
import team.bahor.repositories.auth.AuthUserRepository;

import java.util.UUID;

@SpringBootApplication
@OpenAPIDefinition
@EnableConfigurationProperties({
        ServerProperties.class, OpenApiProperty.class
})
public class BoomAcademyApplication {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public BoomAcademyApplication(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(BoomAcademyApplication.class, args);
    }


//    @Bean
    CommandLineRunner runner() {
        return (args) -> {
            authUserRepository.deleteAll();
            String encode = passwordEncoder.encode("123");
            System.out.println("encode = " + encode);
            AuthUser admin = AuthUser.childBuilder()
                    .id(UUID.randomUUID().toString())
                    .username("admin")
                    .password(encode)
                    .role(Role.ADMIN)
                    .status((short)0)
                    .fullName("adminov admin adminovich")
                    .phone("+998906543210")
                    .balance(0D)
                    .email("abdumajidabdullatipov@gmail.com")
                    .build();
            authUserRepository.save(admin);
        };
    }
}

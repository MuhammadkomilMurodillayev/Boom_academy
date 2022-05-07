package team.bahor.services.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.bahor.config.security.JwtUtils;
import team.bahor.dto.auth.AuthUserDto;
import team.bahor.dto.auth.SessionDto;
import team.bahor.dto.responce.AppErrorDto;
import team.bahor.dto.responce.DataDto;
import team.bahor.dto.user.UserCreateDto;
import team.bahor.dto.user.UserDto;
import team.bahor.dto.user.UserUpdateDto;
import team.bahor.entity.user.AuthUser;
import team.bahor.entity.user.UserActivationCode;
import team.bahor.enums.Role;
import team.bahor.exeptions.user.AuthUserEmailAlreadyTakenExeption;
import team.bahor.exeptions.user.RefreshTokenIsMissing;
import team.bahor.mappers.user.AuthUserMapper;
import team.bahor.properties.ServerProperties;
import team.bahor.repositories.auth.AuthUserRepository;
import team.bahor.repositories.auth.UserActivationCodeRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.validators.user.AuthUserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@Slf4j
public class AuthUserServiceImp extends AbstractService<
        AuthUserRepository,
        AuthUserMapper,
        AuthUserValidator> implements AuthUserService, UserDetailsService {

    private final JavaMailSender javaMailSender;
    private final ServerProperties serverProperties;
    private final ObjectMapper objectMapper;
    private final UserActivationCodeRepository userActivationCodeRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AuthUserServiceImp(@Qualifier("authUserMapperImpl") AuthUserMapper mapper,
                              AuthUserValidator validator,
                              AuthUserRepository repository,
                              JavaMailSender javaMailSender,
                              ServerProperties serverProperties,
                              ObjectMapper objectMapper, UserActivationCodeRepository userActivationCodeRepository, PasswordEncoder passwordEncoder) {
        super(mapper, validator, repository);
        this.javaMailSender = javaMailSender;
        this.serverProperties = serverProperties;
        this.objectMapper = objectMapper;
        this.userActivationCodeRepository = userActivationCodeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<DataDto<SessionDto>> getToken(AuthUserDto dto) {

        try {

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(serverProperties.getServerUrl() + "/api/login");
            byte[] bytes = objectMapper.writeValueAsBytes(dto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));

            HttpResponse response = httpclient.execute(httppost);

            JsonNode json_auth = objectMapper.readTree(EntityUtils.toString(response.getEntity()));

            if (json_auth.has("success") && json_auth.get("success").asBoolean()) {
                JsonNode node = json_auth.get("data");
                SessionDto sessionDto = objectMapper.readValue(node.toString(), SessionDto.class);
                return new ResponseEntity<>(new DataDto<>(sessionDto), HttpStatus.OK);
            }
            return new ResponseEntity<>(new DataDto<>(objectMapper.readValue(json_auth.get("error").toString(),
                    AppErrorDto.class)), HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .message(e.getLocalizedMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build()), HttpStatus.OK);
        }
    }


    public ResponseEntity<DataDto<SessionDto>> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());

                Algorithm algorithm = JwtUtils.getAlgorithm();
                Date expiryForAccessToken = JwtUtils.getExpiryForRefreshToken();
                Date expiryForRefreshToken = JwtUtils.getExpiryForRefreshToken();

                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                team.bahor.config.security.UserDetails user = new team.bahor.config.security.UserDetails(repository.findByUsernameAndDeletedFalse(username).get());

                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(expiryForAccessToken)
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .withClaim("id",user.getId())
                .withClaim("status", (int) user.getStatus())
                .withClaim("deleted",user.isDeleted())

                        .sign(JwtUtils.getAlgorithm());

                SessionDto sessionDto = SessionDto.builder()
                        .accessToken(accessToken)
                        .accessTokenExpiry(expiryForAccessToken.getTime())
                        .refreshToken(refreshToken)
                        .refreshTokenExpiry(expiryForRefreshToken.getTime())
                        .issuedAt(System.currentTimeMillis())
                        .build();
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), new DataDto<>(sessionDto));
            } catch (Exception exception) {
                log.error("Error logging in: {}", exception.getMessage());
                response.setHeader("error", exception.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RefreshTokenIsMissing("Refresh token is missing");
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = repository.findByUsernameAndDeletedFalse(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });
        return new team.bahor.config.security.UserDetails(user);
    }


    public void blocked(String id) {
        repository.blocked(id);
    }

    @Override
    public String create(UserCreateDto createDto) {

        AuthUser authUser = repository.findByEmailOrUsername(createDto.getEmail(), createDto.getUsername());

        if (Objects.nonNull(authUser))
            throw new AuthUserEmailAlreadyTakenExeption("Bad request !!!");

        String random = UUID.randomUUID().toString();
        authUser = mapper.fromCreateDto(createDto);
        authUser.setPassword(passwordEncoder.encode(createDto.getPassword()));
        authUser.setId(random);
        authUser.setRole(Role.USER);
        authUser.setStatus((short) 110);
        authUser.setBalance(0.0);
        AuthUser save = repository.save(authUser);

        UserActivationCode userActivationCode = new UserActivationCode(save.getId(), random, save.getEmail(), LocalDateTime.now().plusHours(2));
        userActivationCodeRepository.save(userActivationCode);
        System.out.println("message = " + "<a href='http://localhost:8080/api/auth/verifyEmail?activationCode=" + random + "&email=" + createDto.getEmail() + "'>Confirmation</a>");
//        sendEmail(createDto.getEmail(), random);
        return "Account created. You can activated account with email !!!";
    }

    @Override
    public void delete(String id) {
        repository.deleted(id);
    }

    @Override
    public void update(UserUpdateDto updateDto) {

    }

    @Override
    public UserDto get(String id) {
        return null;
    }

    @Override
    public List<UserDto> getAll() {
        return null;
    }

    public String verifyEmail(String activationCode, String email) {
        String userId = validator.checksActivationCode(activationCode, email);
        repository.changeStatusActive(userId);
        return "Your profile active !";
    }

//        if (Objects.isNull(userActivationCode))
//            return "No Activation";


    //Todo ishlatish kerak shuni togolar asincron iloji bolsa
   // @Async
    public boolean sendEmail(String sendingEmail, String key) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("boom_academy@gmail.com");
            message.setTo(sendingEmail);
            message.setSubject("SECURITY REGISTRATION");
            message.setText("<a href='http://localhost:8080/api/auth/verifyEmail?activationCode=" + key + "&email=" + sendingEmail + "'>Confirmation</a>");
            System.out.println("message = " + "<a href='http://localhost:8080/api/auth/verifyEmail?activationCode=" + key + "&email=" + sendingEmail + "'>Confirmation</a>");
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}


package team.bahor.exeptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthUserNotFoundExeption extends RuntimeException {
    public AuthUserNotFoundExeption(String message) {
        super(message);
    }
}

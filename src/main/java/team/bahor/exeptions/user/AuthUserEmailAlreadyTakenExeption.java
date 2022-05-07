package team.bahor.exeptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AuthUserEmailAlreadyTakenExeption extends RuntimeException {
    public AuthUserEmailAlreadyTakenExeption(String message) {
        super(message);
    }
}

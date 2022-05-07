package team.bahor.exeptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
public class ActivationCodeInvalidException extends RuntimeException {
    public ActivationCodeInvalidException(String message) {
        super(message);
    }

}

package team.bahor.exeptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RefreshTokenIsMissing extends RuntimeException{
    public RefreshTokenIsMissing(String message) {
        super(message);
    }
}

package team.bahor.exeptions.exam;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotValidOnCreateException extends RuntimeException{
    public NotValidOnCreateException(String message) {
        super(message);
    }
}

package team.bahor.exeptions.exam;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundQuestionTypeException extends RuntimeException{
    public NotFoundQuestionTypeException(String message) {
        super(message);
    }
}

package team.bahor.exeptions.course.saved;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class SavedForbiddenException extends RuntimeException{
    public SavedForbiddenException(String message){
        super(message);
    }
}

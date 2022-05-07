package team.bahor.exeptions.tags;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class TagAlreadyExistsException extends RuntimeException{
    public TagAlreadyExistsException(String message){
        super(message);
    }
}

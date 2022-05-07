package team.bahor.exeptions.tags;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TagsNotFoundException extends RuntimeException{
    public TagsNotFoundException(String message){
        super(message);
    }
}

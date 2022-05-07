package team.bahor.exeptions.lesson.notes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotesToLessonsNotFoundException extends RuntimeException{
    public NotesToLessonsNotFoundException(String message){
        super(message);
    }
}

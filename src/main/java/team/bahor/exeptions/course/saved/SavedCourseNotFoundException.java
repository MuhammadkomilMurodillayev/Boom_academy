package team.bahor.exeptions.course.saved;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SavedCourseNotFoundException extends RuntimeException{
    public SavedCourseNotFoundException(String message){
        super(message);
    }
}

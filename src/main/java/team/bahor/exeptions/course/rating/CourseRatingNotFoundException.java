package team.bahor.exeptions.course.rating;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CourseRatingNotFoundException extends RuntimeException{
    public CourseRatingNotFoundException(String message){
        super(message);
    }
}

package team.bahor.exeptions.course.rating;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CourseRatingInvalidException extends RuntimeException {
    public CourseRatingInvalidException(String message) {
        super(message);
    }
}

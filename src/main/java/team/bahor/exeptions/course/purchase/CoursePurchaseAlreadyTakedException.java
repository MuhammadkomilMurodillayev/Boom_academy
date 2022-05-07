package team.bahor.exeptions.course.purchase;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CoursePurchaseAlreadyTakedException extends RuntimeException{
    public CoursePurchaseAlreadyTakedException(String message){
        super(message);
    }
}

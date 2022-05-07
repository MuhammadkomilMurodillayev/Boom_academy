package team.bahor.exeptions.lesson.question;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LessonQuestionNotFoundException extends RuntimeException {
    public LessonQuestionNotFoundException(String message) {
        super(message);
    }
}

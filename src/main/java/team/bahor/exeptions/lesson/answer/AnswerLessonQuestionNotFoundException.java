package team.bahor.exeptions.lesson.answer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnswerLessonQuestionNotFoundException extends RuntimeException{
    public AnswerLessonQuestionNotFoundException(String messaga){
        super(messaga);
    }
}

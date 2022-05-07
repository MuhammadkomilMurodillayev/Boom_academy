package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class NotFoundExamQuestion extends ValidationException {
    public NotFoundExamQuestion(String message) {
        super(message);
    }

    public NotFoundExamQuestion(String message, Throwable cause) {
        super(message, cause);
    }
}

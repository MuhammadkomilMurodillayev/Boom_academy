package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class NotFoundAnswerToExamQuestion extends ValidationException {
    public NotFoundAnswerToExamQuestion(String message) {
        super(message);
    }

    public NotFoundAnswerToExamQuestion(String message, Throwable cause) {
        super(message, cause);
    }
}

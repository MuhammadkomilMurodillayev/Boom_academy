package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class AlreadySavedExamException extends ValidationException {
    public AlreadySavedExamException(String message) {
        super(message);
    }

    public AlreadySavedExamException(String message, Throwable cause) {
        super(message, cause);
    }
}

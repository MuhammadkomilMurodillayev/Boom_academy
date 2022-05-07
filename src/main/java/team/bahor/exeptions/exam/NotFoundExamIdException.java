package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class NotFoundExamIdException extends ValidationException {
    public NotFoundExamIdException(String message) {
        super(message);
    }

    public NotFoundExamIdException(String message, Throwable cause) {
        super(message, cause);
    }
}

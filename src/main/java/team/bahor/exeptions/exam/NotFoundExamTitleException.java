package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class NotFoundExamTitleException extends ValidationException {
    public NotFoundExamTitleException(String message) {
        super(message);
    }

    public NotFoundExamTitleException(String message, Throwable cause) {
        super(message, cause);
    }
}

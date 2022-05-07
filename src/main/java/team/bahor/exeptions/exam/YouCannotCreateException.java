package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class YouCannotCreateException extends ValidationException {
    public YouCannotCreateException(String message) {
        super(message);
    }

    public YouCannotCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}

package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class BadCredentialsUpdateException extends ValidationException {

    public BadCredentialsUpdateException(String message) {
        super(message);
    }

    public BadCredentialsUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}

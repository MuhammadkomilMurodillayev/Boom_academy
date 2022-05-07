package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class BadCredentialsSolve extends ValidationException {
    public BadCredentialsSolve(String message) {
        super(message);
    }

    public BadCredentialsSolve(String message, Throwable cause) {
        super(message, cause);
    }
}

package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class SolveBadCredentialException extends ValidationException {
    public SolveBadCredentialException(String message) {
        super(message);
    }

    public SolveBadCredentialException(String message, Throwable cause) {
        super(message, cause);
    }
}

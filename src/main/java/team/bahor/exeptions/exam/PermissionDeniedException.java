package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class PermissionDeniedException extends ValidationException {
    public PermissionDeniedException(String message) {
        super(message);
    }

    public PermissionDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}

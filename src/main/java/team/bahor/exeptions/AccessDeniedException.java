package team.bahor.exeptions;

import team.bahor.exeptions.base.BaseException;

public class AccessDeniedException extends BaseException {
    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}

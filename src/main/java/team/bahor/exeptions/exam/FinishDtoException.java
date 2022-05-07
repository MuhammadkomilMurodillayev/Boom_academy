package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class FinishDtoException extends ValidationException {
    public FinishDtoException(String message) {
        super(message);
    }

    public FinishDtoException(String message, Throwable cause) {
        super(message, cause);
    }
}

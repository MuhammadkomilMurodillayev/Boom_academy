package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class FinishTimeException extends ValidationException {
    public FinishTimeException(String message) {
        super(message);
    }

    public FinishTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}

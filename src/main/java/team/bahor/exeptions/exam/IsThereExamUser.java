package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class IsThereExamUser extends ValidationException {
    public IsThereExamUser(String message) {
        super(message);
    }

    public IsThereExamUser(String message, Throwable cause) {
        super(message, cause);
    }
}

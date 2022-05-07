package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class IsCanThisExam extends ValidationException {
    public IsCanThisExam(String message) {
        super(message);
    }

    public IsCanThisExam(String message, Throwable cause) {
        super(message, cause);
    }
}

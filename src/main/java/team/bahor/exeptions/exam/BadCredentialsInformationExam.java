package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class BadCredentialsInformationExam extends ValidationException {
    public BadCredentialsInformationExam(String message) {
        super(message);
    }

    public BadCredentialsInformationExam(String message, Throwable cause) {
        super(message, cause);
    }
}

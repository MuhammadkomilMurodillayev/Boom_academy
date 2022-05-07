package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class BadCredentialsExamQuestionCreate extends ValidationException {
    public BadCredentialsExamQuestionCreate(String message) {

        super(message);
    }

    public BadCredentialsExamQuestionCreate(String message, Throwable cause) {
        super(message, cause);
    }
}

package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class NotFoundCourseException extends ValidationException {
    public NotFoundCourseException(String message) {
        super(message);
    }

    public NotFoundCourseException(String message, Throwable cause) {
        super(message, cause);
    }
}

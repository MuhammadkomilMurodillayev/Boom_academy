package team.bahor.exeptions.exam;

import team.bahor.exeptions.ValidationException;

public class NotFoundExamCourseIdException extends ValidationException {
    public NotFoundExamCourseIdException(String message) {
        super(message);
    }

    public NotFoundExamCourseIdException(String message, Throwable cause) {
        super(message, cause);
    }
}

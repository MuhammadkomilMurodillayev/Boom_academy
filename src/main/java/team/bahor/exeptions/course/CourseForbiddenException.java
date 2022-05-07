package team.bahor.exeptions.course;

import team.bahor.exeptions.ValidationException;

public class CourseForbiddenException extends ValidationException {
    public CourseForbiddenException(String message) {
        super(message);

    }
}

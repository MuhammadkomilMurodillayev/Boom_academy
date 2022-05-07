package team.bahor.exeptions.course;

import team.bahor.exeptions.ValidationException;

public class CategoryNotAvailableException extends ValidationException {
    public CategoryNotAvailableException(String message) {
        super(message);
    }
}

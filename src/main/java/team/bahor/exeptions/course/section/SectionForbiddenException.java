package team.bahor.exeptions.course.section;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class SectionForbiddenException extends RuntimeException {
    public SectionForbiddenException(String message) {
        super(message);
    }
}


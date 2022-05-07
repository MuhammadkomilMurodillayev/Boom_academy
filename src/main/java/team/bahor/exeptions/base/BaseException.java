package team.bahor.exeptions.base;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

public class BaseException extends RuntimeException {
    private Timestamp timestamp;
    private String developerMessage;
    private String path;

    public BaseException(String message) {
        super(message);
        this.timestamp = new Timestamp(new Date().getTime());
        this.developerMessage = Arrays.toString(super.getStackTrace());
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.timestamp = new Timestamp(new Date().getTime());
        this.developerMessage = Arrays.toString(super.getStackTrace());

    }
}

package team.bahor.exeptions;

public class NotAllowedException extends ValidationException {
    public NotAllowedException(String message) {
        super(message);
    }
}

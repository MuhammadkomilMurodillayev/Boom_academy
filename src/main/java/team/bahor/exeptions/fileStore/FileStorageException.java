package team.bahor.exeptions.fileStore;

import team.bahor.exeptions.ValidationException;

public class FileStorageException extends ValidationException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

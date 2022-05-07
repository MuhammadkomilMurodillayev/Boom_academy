package team.bahor.exeptions.fileStore;


public class StorageFileNotFoundException extends FileStorageException{
    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

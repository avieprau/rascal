package jgit.object;

public class CorruptedObjectException extends Exception {
    public CorruptedObjectException(String objectName, String message) {
        super(String.format("Object with name '%s' is corrupted: %s", objectName, message));
    }

    public CorruptedObjectException(String objectName, Throwable cause) {
        super(String.format("Object with name '%s' is corrupted", objectName), cause);
    }
}

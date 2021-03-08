package ee.taltech.iti0202.files.input;

public class FileReaderException extends RuntimeException {
    /**
     * Constructor
     *
     * @param message exception message
     * @param reason  exception reason
     */
    public FileReaderException(String message, Throwable reason) {
        super(message, reason);
    }
}

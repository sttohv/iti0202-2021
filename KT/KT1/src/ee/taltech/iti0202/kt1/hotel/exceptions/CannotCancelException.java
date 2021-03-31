package ee.taltech.iti0202.kt1.hotel.exceptions;

public class CannotCancelException extends Exception {
    private final Reason reason;

    public enum Reason {
        SUITE_CANNOT_BE_CANCELED,
        SUITE_NOT_BOOKED,
        NO_SUCH_SUITE
    }

    /**
     * New exception
     *
     * @param reason reason
     */
    public CannotCancelException(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}

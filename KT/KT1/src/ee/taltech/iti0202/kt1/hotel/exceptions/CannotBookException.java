package ee.taltech.iti0202.kt1.hotel.exceptions;

public class CannotBookException extends Exception {
    private final Reason reason;

    public enum Reason {
        ROOM_ALREADY_BOOKED,
        NO_AVAILABLE_ROOMS
    }

    /**
     * New exception
     *
     * @param reason reason
     */
    public CannotBookException(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}

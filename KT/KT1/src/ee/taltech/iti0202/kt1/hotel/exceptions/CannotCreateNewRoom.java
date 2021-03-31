package ee.taltech.iti0202.kt1.hotel.exceptions;

public class CannotCreateNewRoom extends Exception {
    private final Reason reason;

    public enum Reason {
        ALREADY_HAS_SAME_ROOM,
        WRONG_TYPE
    }

    /**
     * New exception
     *
     * @param reason reason
     */
    public CannotCreateNewRoom(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}

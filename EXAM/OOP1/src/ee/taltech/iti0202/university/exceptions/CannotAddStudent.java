package ee.taltech.iti0202.university.exceptions;

public class CannotAddStudent extends Exception {
    public enum Reason {
        TOO_YOUNG, ALREADY_IN_UNI
    }

    private Reason reason;

    /**
     * Creates a new error
     *
     * @param reason reason
     */
    public CannotAddStudent(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}

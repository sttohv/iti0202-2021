package ee.taltech.iti0202.university.exceptions;

public class CannotDeclareException extends Exception{
    public enum Reason {
        NO_SUCH_COURSES,
        WRONG_UNI,
        STUDENT_NOT_ENROLLED_IN_UNI,
        NOT_ENOUGH_ECTS
    }

    private Reason reason;

    /**
     * Creates a new exception
     *
     * @param reason reason
     */
    public CannotDeclareException(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}

package ee.taltech.iti0202.university.exceptions;

public class CannotGradeException extends Exception {
    public enum Reason {
        STUDENT_IS_NOT_ENROLLED,
        STUDENT_AND_COURSE_NOT_IN_THE_SAME_UNI,
        WRONG_GRADE
    }

    private Reason reason;

    /**
     * Creates a new exception
     *
     * @param reason reason
     */
    public CannotGradeException(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}

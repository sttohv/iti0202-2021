package ee.taltech.iti0202.university.exceptions;

public class CannotAddCourse extends Exception {
    public enum Reason {
        ECTS_CANT_BE_NEGATIVE,
        COURSE_ALREADY_HAS_UNI_ASSIGNED,
        ALREADY_ENROLLED_TO_COURSE,
        STUDENT_AND_COURSE_IN_DIFFERENT_UNIS
    }

    private Reason reason;

    /**
     * Creates a new error
     *
     * @param reason reason
     */
    public CannotAddCourse(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}

package ee.taltech.iti0202.university.course;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.exceptions.CannotAddCourseException;
import ee.taltech.iti0202.university.teacher.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CourseTest {

    public static final int NEG_CPOINT = -6;
    public static final int MIN_CREDIT_POINTS = 6;
    public static final int MAX_CREDIT_POINTS = 60;

    @Test
    public void cannotCreateNegativeECTS() {
        University talTech = new University("TalTech", MIN_CREDIT_POINTS, MAX_CREDIT_POINTS);
        Teacher ago = new Teacher("Ago", talTech);
        CannotAddCourseException.Reason reason = null;

        try {
            Course java = new Course("java", NEG_CPOINT, true, ago, Course.Type.CORE);
        } catch (CannotAddCourseException cannotAddCourse) {
            reason = cannotAddCourse.getReason();
        }

        Assertions.assertEquals(CannotAddCourseException.Reason.ECTS_CANT_BE_NEGATIVE, reason);
    }

    @Test
    public void createCorrectCourse() throws CannotAddCourseException {
        University talTech = new University("TalTech", MIN_CREDIT_POINTS
                , MAX_CREDIT_POINTS);
        Teacher ago = new Teacher("Ago", talTech);
        Course java = new Course("java", MIN_CREDIT_POINTS, true, ago, Course.Type.SPECIAL);

        Assertions.assertNull(java.getUniversity());
        Assertions.assertEquals("java", java.getName());

    }

}

package ee.taltech.iti0202.university.course;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.exceptions.CannotAddCourseException;
import ee.taltech.iti0202.university.teacher.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CourseTest {
    @Test
    public void cannotCreateNegativeECTS() {
        University talTech = new University("TalTech", 6, 60);
        Teacher ago = new Teacher("Ago", talTech);
        CannotAddCourseException.Reason reason = null;

        try {
            Course java = new Course("java", -6, true, ago, Course.Type.CORE);
        } catch (CannotAddCourseException cannotAddCourse) {
            reason = cannotAddCourse.getReason();
        }

        Assertions.assertEquals(CannotAddCourseException.Reason.ECTS_CANT_BE_NEGATIVE, reason);
    }

    @Test
    public void createCorrectCourse() throws CannotAddCourseException {
        University talTech = new University("TalTech", 6, 60);
        Teacher ago = new Teacher("Ago", talTech);
        Course java = new Course("java", 6, true, ago, Course.Type.SPECIAL);

        Assertions.assertNull(java.getUniversity());
        Assertions.assertEquals("java", java.getName());

    }

}

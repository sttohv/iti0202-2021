package ee.taltech.iti0202.university.teacher;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exceptions.CannotAddCourseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TeacherTest {

    public static final int RANDOM_SMALL_CPOINT1 = 6;
    public static final int RANDOM_BIG_CPOINT1 = 10;

    @Test
    public void TestCreatingCorrectTeacher() {
        University taltech = new University("taltech", RANDOM_SMALL_CPOINT1, RANDOM_BIG_CPOINT1);
        Teacher ago = new Teacher("Ago", taltech);

        Assertions.assertEquals("Ago", ago.getName());
        Assertions.assertEquals(0, ago.getCourseList().size());
    }

    @Test
    public void TestAddCourseAlreadyIn() throws CannotAddCourseException {
        University taltech = new University("taltech", RANDOM_SMALL_CPOINT1, RANDOM_BIG_CPOINT1);
        Teacher ago = new Teacher("Ago", taltech);
        Course java = new Course("java", RANDOM_SMALL_CPOINT1, true, ago, Course.Type.BASIC);

        ago.addCourses(java);

        Assertions.assertEquals(1, ago.getCourseList().size());
    }
}

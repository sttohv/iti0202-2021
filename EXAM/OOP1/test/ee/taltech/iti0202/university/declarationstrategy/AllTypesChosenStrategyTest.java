package ee.taltech.iti0202.university.declarationstrategy;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exceptions.CannotAddCourseException;
import ee.taltech.iti0202.university.exceptions.CannotAddStudentException;
import ee.taltech.iti0202.university.exceptions.CannotDeclareException;
import ee.taltech.iti0202.university.student.Student;
import ee.taltech.iti0202.university.studyprogramme.StudyProgramme;
import ee.taltech.iti0202.university.teacher.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class AllTypesChosenStrategyTest {
    private Teacher ago;
    private University ttu;

    private StudyProgramme businessIt;
    private StudyProgramme informatics;

    private Student stina;
    private Student lorenz;

    private Course course1;
    private Course course2;
    private Course course3;
    private Course course4;

    @BeforeEach
    void setUp() throws CannotAddStudentException, CannotAddCourseException {
        ttu = new University("TalTech", 3, 30);

        ago = new Teacher("Ago", ttu);

        course1 = new Course("course 1", 2, true, ago, Course.Type.CORE);
        course2 = new Course("course 2", 6, true, ago, Course.Type.BASIC);
        course3 = new Course("course 3", 1, true, ago, Course.Type.GENERAL);
        course4 = new Course("course 4", 2, true, ago, Course.Type.SPECIAL);

        List<Course> allTypes = List.of(course1, course2, course3, course4);
        List<Course> oneMissing = List.of(course1, course2, course3);

        ttu.addCourse(course1);
        ttu.addCourse(course2);
        ttu.addCourse(course3);
        ttu.addCourse(course4);

        businessIt = new StudyProgramme("businessIt", allTypes, ttu);
        informatics = new StudyProgramme("informatics", oneMissing, ttu);

        stina = new Student("Stina", 20, businessIt);
        lorenz = new Student("Lorenz", 22, informatics);

        ttu.addStudent(stina);
        ttu.addStudent(lorenz);
    }

    @Test
    public void testNotEnoughEcts() throws CannotDeclareException {
        ttu.setMinCreditPoints(12);
        lorenz.setStrategy(new AllTypesChosenStrategy(lorenz));
        CannotDeclareException.Reason reason = null;
        try {
            ttu.declareCourses(lorenz);
        } catch (CannotDeclareException e) {
            reason = e.getReason();
        }
        Assertions.assertEquals(CannotDeclareException.Reason.NOT_ENOUGH_ECTS, reason);


    }

    @Test
    public void testAllTypesExist() throws CannotDeclareException {
        stina.setStrategy(new AllTypesChosenStrategy(stina));
        ttu.declareCourses(stina);

        Assertions.assertEquals(4, stina.getOngoingCourses().size());
    }

    @Test
    public void testAllTypesDontExist() throws CannotDeclareException {

        lorenz.setStrategy(new AllTypesChosenStrategy(lorenz));
        CannotDeclareException.Reason reason = null;

        ttu.declareCourses(lorenz);

        Assertions.assertEquals(3, lorenz.getOngoingCourses().size());
    }
}

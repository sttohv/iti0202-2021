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

class EasierCoursesFirstStrategyTest {
    private University ttu;
    private University tu;

    private StudyProgramme businessIt;
    private StudyProgramme informatics;
    private StudyProgramme mathematics;

    private Student stina;
    private Student lorenz;
    private Student liisi;

    private Teacher ago;
    private Teacher mari;

    private Course course1;
    private Course course2;
    private Course course3;
    private Course course4;

    private Course course5;
    private Course course6;
    private Course course7;
    private Course course8;

    private Course course9;

    @BeforeEach
    void setUp() throws CannotAddStudentException, CannotAddCourseException {
        ttu = new University("TalTech", 3, 30);
        tu = new University("TÜ", 6, 10);

        ago = new Teacher("Ago", ttu);
        mari = new Teacher("Mari", tu);

        course1 = new Course("course 1", 2, true, ago, Course.Type.CORE);
        course2 = new Course("course 2", 6, true, ago, Course.Type.BASIC);
        course3 = new Course("course 3", 1, true, ago, Course.Type.GENERAL);
        course4 = new Course("course 4", 2, true, ago, Course.Type.SPECIAL);

        course5 = new Course("course 1", 2, true, ago, Course.Type.CORE);
        course6 = new Course("course 2", 6, true, ago, Course.Type.GENERAL);
        course7 = new Course("course 3", 1, true, ago, Course.Type.SPECIAL);
        course8 = new Course("course 4", 2, true, ago, Course.Type.BASIC);

        course9 = new Course("course 9", 100, false, ago, Course.Type.CORE);

        List<Course> ttuCourses = List.of(course1, course2, course3, course4);
        List<Course> tuCourses = List.of(course5, course6, course7, course8);
        List<Course> oneBigCourse = List.of(course9);

        ttu.addCourse(course1);
        ttu.addCourse(course2);
        ttu.addCourse(course3);
        ttu.addCourse(course4);

        businessIt = new StudyProgramme("businessIt", ttuCourses, ttu);
        informatics = new StudyProgramme("informatics", tuCourses, tu);
        mathematics = new StudyProgramme("math", oneBigCourse, ttu);

        stina = new Student("Stina", 20, businessIt);
        lorenz = new Student("Lorenz", 22, informatics);
        liisi = new Student("Liisi", 20, mathematics);

        ttu.addStudent(stina);
        tu.addStudent(lorenz);
    }

    @Test
    public void TestEasyStrategyAllCorrect() throws CannotDeclareException {
        EasierCoursesFirstStrategy easy = new EasierCoursesFirstStrategy(stina);
        stina.setStrategy(easy);
        ttu.declareCourses(stina);

        Assertions.assertEquals(2, stina.getOngoingCourses().size());

    }

    @Test
    public void TestEasyStrategyWhenHasOngoingCourses() {

    }

    @Test
    public void TestStrategyInCorrectUni() {
        EasierCoursesFirstStrategy easy = new EasierCoursesFirstStrategy(stina);
        stina.setStrategy(easy);
        CannotDeclareException.Reason reason = null;
        try {
            tu.declareCourses(stina);
        } catch (CannotDeclareException e) {
            reason = e.getReason();
        }

        Assertions.assertEquals(CannotDeclareException.Reason.WRONG_UNI, reason);
    }

    @Test
    public void TestStrategyWhereStudentNotEnrolled() {
        EasierCoursesFirstStrategy easy = new EasierCoursesFirstStrategy(liisi);
        liisi.setStrategy(easy);
        CannotDeclareException.Reason reason = null;
        try {
            ttu.declareCourses(liisi);
        } catch (CannotDeclareException e) {
            reason = e.getReason();
        }
        Assertions.assertEquals(CannotDeclareException.Reason.STUDENT_NOT_ENROLLED_IN_UNI, reason);
    }

    @Test
    public void TestStrategyWhenNoMatchingCourses() throws CannotAddStudentException {
        ttu.addStudent(liisi);
        EasierCoursesFirstStrategy easy = new EasierCoursesFirstStrategy(liisi);
        liisi.setStrategy(easy);
        CannotDeclareException.Reason reason = null;
        try {
            ttu.declareCourses(liisi);
        } catch (CannotDeclareException e) {
            reason = e.getReason();
        }
        Assertions.assertEquals(CannotDeclareException.Reason.NO_SUCH_COURSES, reason);
    }


}

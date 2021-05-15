package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exceptions.CannotAddCourseException;
import ee.taltech.iti0202.university.exceptions.CannotAddStudentException;
import ee.taltech.iti0202.university.exceptions.CannotGradeException;
import ee.taltech.iti0202.university.student.Student;
import ee.taltech.iti0202.university.studyprogramme.StudyProgramme;
import ee.taltech.iti0202.university.teacher.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class UniversityTestGrading {
    private University ttu;
    private University tu;
    private Teacher ago;
    private Teacher mari;
    private Course python;
    private Course java;
    private Course math;
    private Student stina;
    private StudyProgramme informatics;


    @BeforeEach
    void setUp() throws CannotAddCourseException, CannotAddStudentException {
        ttu = new University("TalTech", 3, 30);
        tu = new University("TÜ", 6, 10);

        ago = new Teacher("Ago", ttu);
        mari = new Teacher("Mari", tu);

        java = new Course("java", 6, true, ago, Course.Type.SPECIAL);
        ttu.addCourse(java);
        python = new Course("python", 6, false, ago, Course.Type.BASIC);
        ttu.addCourse(python);
        math = new Course("math", 6, true, mari, Course.Type.GENERAL);

        informatics = new StudyProgramme("informatics", List.of(java, python), ttu);
        stina = new Student("Stina", 20, informatics);

        ttu.addStudent(stina);
    }

    @Test
    public void addGradeWithNumbersPassed() throws CannotGradeException, CannotAddCourseException {
        ttu.addStudentToCourse(stina, java);
        ttu.addGrade(stina, java, "5");

        Assertions.assertEquals(List.of("5"), stina.getGrades().get(java));
        Assertions.assertEquals(java, stina.getPassedCourses().get(0));
        Assertions.assertEquals(0, stina.getFailedCourses().size());

    }

    @Test
    public void addGradeWithNumbersFailed() throws CannotAddCourseException, CannotGradeException {
        ttu.addStudentToCourse(stina, java);
        ttu.addGrade(stina, java, "0");

        Assertions.assertEquals(List.of("0"), stina.getGrades().get(java));
        Assertions.assertEquals(java, stina.getFailedCourses().get(0));
        Assertions.assertEquals(0, stina.getPassedCourses().size());
    }

    @Test
    public void TestAddGradeWrongNumber() throws CannotAddCourseException {
        ttu.addStudentToCourse(stina, java);
        CannotGradeException.Reason reason = null;
        try {
            ttu.addGrade(stina, java, "8");
        } catch (CannotGradeException e) {
            reason = e.getReason();
        }
        Assertions.assertEquals(CannotGradeException.Reason.WRONG_GRADE, reason);


    }


    @Test
    public void addGradePassed() throws CannotAddCourseException, CannotGradeException {
        ttu.addStudentToCourse(stina, python);
        ttu.addGrade(stina, python, "pass");

        Assertions.assertEquals(List.of("pass"), stina.getGrades().get(python));
        Assertions.assertEquals(python, stina.getPassedCourses().get(0));
        Assertions.assertEquals(0, stina.getFailedCourses().size());

    }

    @Test
    public void addGradeFailed() throws CannotAddCourseException, CannotGradeException {
        ttu.addStudentToCourse(stina, python);
        ttu.addGrade(stina, python, "fail");

        Assertions.assertEquals(List.of("fail"), stina.getGrades().get(python));
        Assertions.assertEquals(python, stina.getFailedCourses().get(0));
        Assertions.assertEquals(0, stina.getPassedCourses().size());


    }

    @Test
    public void TestAddGradeWrongLetter() throws CannotAddCourseException {
        ttu.addStudentToCourse(stina, python);
        CannotGradeException.Reason reason = null;
        try {
            ttu.addGrade(stina, python, "0");
        } catch (CannotGradeException e) {
            reason = e.getReason();
        }
        Assertions.assertEquals(CannotGradeException.Reason.WRONG_GRADE, reason);


    }

    @Test
    public void addGradeCourseStudentNotSameUni() throws CannotAddCourseException {
        ttu.addStudentToCourse(stina, python);
        CannotGradeException.Reason reason = null;
        try {
            tu.addGrade(stina, python, "fail");
        } catch (CannotGradeException e) {
            reason = e.getReason();
        }
        Assertions.assertEquals(CannotGradeException.Reason.STUDENT_AND_COURSE_NOT_IN_THE_SAME_UNI, reason);


    }

    @Test
    public void addStudentNotEnrolledToCorrectCourse() throws CannotAddCourseException {
        ttu.addStudentToCourse(stina, python);
        CannotGradeException.Reason reason = null;
        try {
            ttu.addGrade(stina, math, "0");
        } catch (CannotGradeException e) {
            reason = e.getReason();
        }
        Assertions.assertEquals(CannotGradeException.Reason.STUDENT_IS_NOT_ENROLLED, reason);

    }


}
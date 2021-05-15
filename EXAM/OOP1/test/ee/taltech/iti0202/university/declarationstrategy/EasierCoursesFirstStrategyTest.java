package ee.taltech.iti0202.university.declarationstrategy;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exceptions.CannotAddCourseException;
import ee.taltech.iti0202.university.exceptions.CannotAddStudentException;
import ee.taltech.iti0202.university.student.Student;
import ee.taltech.iti0202.university.studyprogramme.StudyProgramme;
import ee.taltech.iti0202.university.teacher.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class EasierCoursesFirstStrategyTest {
    private University ttu;
    private University tu;

    private StudyProgramme businessIt;
    private StudyProgramme informatics;

    private Student stina;
    private Student lorenz;

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

    @BeforeEach
    public void SetUp() throws CannotAddStudentException, CannotAddCourseException {
        ttu = new University("TalTech", 3, 30);
        tu = new University("TÜ", 6, 10);

        ago = new Teacher("Ago", ttu);
        mari = new Teacher("Mari", tu);

        course1 = new Course("course 1", 2, true, ago);
        course2 = new Course("course 2", 6, true, ago);
        course3 = new Course("course 3", 1, true, ago);
        course4 = new Course("course 4", 2, true, ago);

        course5 = new Course("course 1", 2, true, ago);
        course6 = new Course("course 2", 6, true, ago);
        course7 = new Course("course 3", 1, true, ago);
        course8 = new Course("course 4", 2, true, ago);

        List<Course> ttuCourses = List.of(course1, course2, course3, course4);
        List<Course> tuCourses = List.of(course5, course6, course7, course8);

        ttu.addCourse(course1);
        ttu.addCourse(course2);
        ttu.addCourse(course3);
        ttu.addCourse(course4);

        businessIt = new StudyProgramme("businessIt", ttuCourses, ttu);
        informatics = new StudyProgramme("informatics", tuCourses, tu);

        stina = new Student("Stina", 20, businessIt);
        lorenz = new Student("Lorenz", 22, informatics);



        ttu.addStudent(stina);
        tu.addStudent(lorenz);
    }

    @Test
    public void TestEasyStrategyAllCorrect(){

    }


}
package ee.taltech.iti0202.university;


import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exceptions.CannotAddCourseException;
import ee.taltech.iti0202.university.exceptions.CannotAddStudentException;
import ee.taltech.iti0202.university.student.Student;
import ee.taltech.iti0202.university.studyprogramme.StudyProgramme;
import ee.taltech.iti0202.university.teacher.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class UniversityTestAddStudentsToCourses {
    @Test
    public void addStudentToCourseDifferentUni() throws CannotAddCourseException, CannotAddStudentException {
        University ttu = new University("ttü", 6, 60);
        StudyProgramme businessIt = new StudyProgramme("businessIt", List.of(), ttu);
        Student stina = new Student("Stina", 20, businessIt);
        Teacher ago = new Teacher("Ago", ttu);
        Course java = new Course("java", 6, true, ago, Course.Type.CORE);
        University tu = new University("tü", 12, 60);
        CannotAddCourseException.Reason reason = null;

        tu.addStudent(stina);
        ttu.addCourse(java);

        try {
            ttu.addStudentToCourse(stina, java);
        } catch (CannotAddCourseException cannotAddCourse) {
            reason = cannotAddCourse.getReason();
        }

        Assertions.assertEquals(CannotAddCourseException.Reason.STUDENT_AND_COURSE_IN_DIFFERENT_UNIS, reason);

    }

    @Test
    public void addStudentToCourseWhereAlreadyLearning() throws CannotAddStudentException, CannotAddCourseException {
        University ttu = new University("ttü", 6, 60);
        StudyProgramme businessIt = new StudyProgramme("businessIt", List.of(), ttu);
        Student stina = new Student("Stina", 20, businessIt);
        Teacher ago = new Teacher("Ago", ttu);
        Course java = new Course("java", 6, true, ago, Course.Type.BASIC);
        CannotAddCourseException.Reason reason = null;

        ttu.addStudent(stina);
        ttu.addCourse(java);
        ttu.addStudentToCourse(stina, java);

        try {
            ttu.addStudentToCourse(stina, java);
        } catch (CannotAddCourseException cannotAddCourse) {
            reason = cannotAddCourse.getReason();
        }

        Assertions.assertEquals(CannotAddCourseException.Reason.ALREADY_ENROLLED_TO_COURSE, reason);

    }

    @Test
    public void addCorrectStudentToCourse() throws CannotAddCourseException, CannotAddStudentException {
        University ttu = new University("ttü", 6, 60);
        StudyProgramme businessIt = new StudyProgramme("businessIt", List.of(), ttu);
        Student stina = new Student("Stina", 20, businessIt);
        Teacher ago = new Teacher("Ago", ttu);
        Course java = new Course("java", 6, true, ago, Course.Type.GENERAL);

        ttu.addStudent(stina);
        ttu.addCourse(java);
        ttu.addStudentToCourse(stina, java);

        Assertions.assertEquals(1, stina.getOngoingCourses().size());

    }
}
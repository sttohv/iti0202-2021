package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exceptions.CannotAddCourseException;
import ee.taltech.iti0202.university.teacher.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class UniversityTestAddCourses {

    @Test
    public void addCourseCorrect() throws CannotAddCourseException {
        University talTech = new University("TalTech", 6, 60);
        Teacher ago = new Teacher("Ago", talTech);
        Course java = new Course("java", 6, true, ago, Course.Type.BASIC);

        talTech.addCourse(java);
        Assertions.assertEquals(1, talTech.getCourses().size());
    }

    @Test
    public void addCourseAlreadyInSameUni() throws CannotAddCourseException {
        University talTech = new University("TalTech", 6, 60);
        Teacher ago = new Teacher("Ago", talTech);
        Course java = new Course("java", 6, true, ago, Course.Type.CORE);
        CannotAddCourseException.Reason reason = null;

        talTech.addCourse(java);

        try {
            talTech.addCourse(java);
        } catch (CannotAddCourseException cannotAddCourse) {
            reason = cannotAddCourse.getReason();
        }

        Assertions.assertEquals(CannotAddCourseException.Reason.COURSE_ALREADY_HAS_UNI_ASSIGNED, reason);

    }

    @Test
    public void addCourseAlreadyInDifferentUni() throws CannotAddCourseException {
        University talTech = new University("TalTech", 6, 60);
        University tartu = new University("TÜ", 12, 60);
        Teacher ago = new Teacher("Ago", talTech);
        Course java = new Course("java", 6, true, ago, Course.Type.SPECIAL);
        CannotAddCourseException.Reason reason = null;

        talTech.addCourse(java);
        try {
            tartu.addCourse(java);
        } catch (CannotAddCourseException cannotAddCourse) {
            reason = cannotAddCourse.getReason();
        }

        Assertions.assertEquals(CannotAddCourseException.Reason.COURSE_ALREADY_HAS_UNI_ASSIGNED, reason);
    }


}
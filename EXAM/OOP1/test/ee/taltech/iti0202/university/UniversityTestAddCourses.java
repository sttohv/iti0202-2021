package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exceptions.CannotAddCourseException;
import ee.taltech.iti0202.university.teacher.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class UniversityTestAddCourses {

    public static final int RANDOM_SMALL_CPOINT1 = 6;
    public static final int RANDOM_BIG_CPOINT1 = 60;
    public static final int RANDOM_SMALL_CPOINT2 = 12;

    @Test
    public void addCourseCorrect() throws CannotAddCourseException {
        University talTech = new University("TalTech", RANDOM_SMALL_CPOINT1, RANDOM_BIG_CPOINT1);
        Teacher ago = new Teacher("Ago", talTech);
        Course java = new Course("java", RANDOM_SMALL_CPOINT1, true, ago, Course.Type.BASIC);

        talTech.addCourse(java);
        Assertions.assertEquals(1, talTech.getCourses().size());
    }

    @Test
    public void addCourseAlreadyInSameUni() throws CannotAddCourseException {
        University talTech = new University("TalTech", RANDOM_SMALL_CPOINT1, RANDOM_BIG_CPOINT1);
        Teacher ago = new Teacher("Ago", talTech);
        Course java = new Course("java", RANDOM_SMALL_CPOINT1, true, ago, Course.Type.CORE);
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
        University talTech = new University("TalTech", RANDOM_SMALL_CPOINT1, RANDOM_BIG_CPOINT1);
        University tartu = new University("TÜ", RANDOM_SMALL_CPOINT2, RANDOM_BIG_CPOINT1);
        Teacher ago = new Teacher("Ago", talTech);
        Course java = new Course("java", RANDOM_SMALL_CPOINT1, true, ago, Course.Type.SPECIAL);
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

package ee.taltech.iti0202.university.declarationstrategy;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exceptions.CannotDeclareException;
import ee.taltech.iti0202.university.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AllTypesChosenStrategy implements DeclarationStrategy {
    private List<Course.Type> allTypes = List.of(
            Course.Type.CORE,
            Course.Type.BASIC,
            Course.Type.GENERAL,
            Course.Type.SPECIAL);
    private Student student;

    public AllTypesChosenStrategy(Student student) {
        this.student = student;
    }

    @Override
    public List<Course> getCourses() throws CannotDeclareException {
        List<Course> courses = new ArrayList<>();
        for (Course.Type type : allTypes
        ) {
            Optional<Course> typeCourse = getCourseWithSameType(type, student.getCoursesLeftToTakeFromProgramme());
            typeCourse.ifPresent(courses::add);
        }
        if (isDeclaredEnoughCreditPoints(courses, student.getUniversity())) {
            return courses;
        }else {
            throw new CannotDeclareException(CannotDeclareException.Reason.NOT_ENOUGH_ECTS);
        }
    }

    /**
     * Get course that has the same name if there
     *
     * @param type    course type to be searched
     * @param courses courses to be looked from
     * @return first course that matches
     */
    private Optional<Course> getCourseWithSameType(Course.Type type, List<Course> courses) {
        return courses.stream().filter(i -> i.getType().equals(type)).findFirst();
    }

    /**
     * Checks if chosen courses have enough credit points
     *
     * @param courseList course to be checked
     * @param university university to get min points from
     * @return if chosen courses have enough credit points
     */
    private boolean isDeclaredEnoughCreditPoints(List<Course> courseList, University university) {
        int sum = getSumOfCreditPoints(courseList);
        return sum >= university.getMinCreditPoints();
    }

    /**
     * Adds up all the credit points from entered courses
     *
     * @param courses courses to take credit points
     * @return sum of credit points
     */
    private int getSumOfCreditPoints(List<Course> courses) {
        int sum = 0;
        for (Course course : courses
        ) {
            sum += course.getCreditPoints();
        }
        return sum;
    }
}

package ee.taltech.iti0202.university.declarationstrategy;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exceptions.CannotDeclareException;

import java.util.List;

public interface DeclarationStrategy {
    /**
     * Strategy that declares your courses
     *
     * @return courses
     */
    List<Course> getCourses() throws CannotDeclareException;
}

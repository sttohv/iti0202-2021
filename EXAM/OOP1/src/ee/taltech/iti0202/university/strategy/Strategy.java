package ee.taltech.iti0202.university.strategy;

import ee.taltech.iti0202.university.course.Course;

import java.util.List;

public interface Strategy {
    /**
     * Strategy that declares your courses
     *
     * @return courses
     */
    List<Course> getCourses();
}

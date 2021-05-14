package ee.taltech.iti0202.university.strategy;

import ee.taltech.iti0202.university.course.Course;

import java.util.List;

public interface Strategy {
    public List<Course> getCourses();
}

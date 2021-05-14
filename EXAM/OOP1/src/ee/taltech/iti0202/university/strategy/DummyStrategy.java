package ee.taltech.iti0202.university.strategy;

import ee.taltech.iti0202.university.course.Course;

import java.util.List;

public class DummyStrategy implements Strategy {
    @Override
    public List<Course> getCourses() {
        return null;
    }
}

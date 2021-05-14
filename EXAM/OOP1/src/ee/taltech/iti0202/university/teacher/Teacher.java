package ee.taltech.iti0202.university.teacher;

import ee.taltech.iti0202.university.course.Course;

import java.util.List;

public class Teacher {
    private String name;
    private List<Course> courseList;

    /**
     * Creates a new teacher
     *
     * @param name       teacher name
     * @param courseList teacher courses
     */
    public Teacher(String name, List<Course> courseList) {
        this.name = name;
        this.courseList = courseList;
    }
}

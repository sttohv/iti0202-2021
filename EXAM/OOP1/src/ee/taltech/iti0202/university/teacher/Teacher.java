package ee.taltech.iti0202.university.teacher;

import ee.taltech.iti0202.university.course.Course;

import java.util.List;

public class Teacher {
    private String name;
    private List<Course> courseList;

    public Teacher(String name, List<Course> courseList){
        this.name = name;
        this.courseList = courseList;
    }
}

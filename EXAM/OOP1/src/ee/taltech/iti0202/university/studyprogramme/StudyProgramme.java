package ee.taltech.iti0202.university.studyprogramme;

import ee.taltech.iti0202.university.course.Course;

import java.util.List;

public class StudyProgramme {
    private String name;
    private List<Course> courseList;

    public StudyProgramme(String name, List<Course> courseList) {
        this.name = name;
        this.courseList = courseList;
    }
}

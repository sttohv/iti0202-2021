package ee.taltech.iti0202.university.studyprogramme;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;

import java.util.List;

public class StudyProgramme {
    private String name;
    private List<Course> courseList;
    private University university;

    /**
     * Creates a new study programme
     *
     * @param name       programme name
     * @param courseList programme courses
     * @param university programme university
     */
    public StudyProgramme(String name, List<Course> courseList, University university) {
        this.name = name;
        this.courseList = courseList;
        this.university = university;
    }

    public List<Course> getCourseList() {
        return courseList;
    }
}

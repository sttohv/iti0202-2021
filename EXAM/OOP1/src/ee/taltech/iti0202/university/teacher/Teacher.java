package ee.taltech.iti0202.university.teacher;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String name;
    private List<Course> courseList;
    private University university;

    /**
     * Creates a new teacher
     *
     * @param name       teacher name
     */
    public Teacher(String name, University university) {
        this.name = name;
        this.courseList = new ArrayList<>();
        this.university = university;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void addCourses(Course course){
        if(!courseList.contains(course)){
            courseList.add(course);
        }
    }

    public University getUniversity() {
        return university;
    }
}

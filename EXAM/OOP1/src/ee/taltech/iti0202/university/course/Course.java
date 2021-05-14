package ee.taltech.iti0202.university.course;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.exceptions.CannotAddCourse;
import ee.taltech.iti0202.university.teacher.Teacher;

public class Course {
    private String name;
    private int creditPoints;
    private boolean isGraded;
    private University university;
    private Teacher teacher;

    /**
     * Creates a new course
     *
     * @param name         Course name
     * @param creditPoints Course ECTS's
     * @param isGraded     If courses is graded then it has grades otherwise just passed or not
     * @throws CannotAddCourse why can't add course
     */
    public Course(String name, int creditPoints, boolean isGraded, Teacher teacher) throws CannotAddCourse {
        this.name = name;
        if (creditPoints > 0) {
            this.creditPoints = creditPoints;
        } else {
            throw new CannotAddCourse(CannotAddCourse.Reason.ECTS_CANT_BE_NEGATIVE);
        }
        this.isGraded = isGraded;
        this.teacher = teacher;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public boolean isGraded() {
        return isGraded;
    }
}

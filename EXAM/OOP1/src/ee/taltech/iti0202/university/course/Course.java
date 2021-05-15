package ee.taltech.iti0202.university.course;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.exceptions.CannotAddCourseException;
import ee.taltech.iti0202.university.teacher.Teacher;

public class Course {
    public enum Type {
        GENERAL,
        BASIC,
        CORE,
        SPECIAL
    }

    private String name;
    private int creditPoints;
    private boolean isGraded;
    private University university;
    private Teacher teacher;
    private Type type;

    /**
     * Creates a new course
     *
     * @param name         Course name
     * @param creditPoints Course ECTS's
     * @param isGraded     If courses is graded then it has grades otherwise just passed or not
     * @throws CannotAddCourseException why can't add course
     */
    public Course(String name, int creditPoints, boolean isGraded, Teacher teacher, Type type) throws CannotAddCourseException {
        this.name = name;
        if (creditPoints > 0) {
            this.creditPoints = creditPoints;
        } else {
            throw new CannotAddCourseException(CannotAddCourseException.Reason.ECTS_CANT_BE_NEGATIVE);
        }
        this.isGraded = isGraded;
        setTeacher(teacher);
        this.type = type;
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

    public String getName() {
        return name;
    }

    public int getCreditPoints() {
        return creditPoints;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        if (teacher.getUniversity().equals(university)) {
            this.teacher = teacher;
            teacher.addCourses(this);
        }
    }

    public Type getType() {
        return type;
    }
}

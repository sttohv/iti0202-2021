package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exceptions.CannotAddCourse;
import ee.taltech.iti0202.university.exceptions.CannotAddStudent;
import ee.taltech.iti0202.university.exceptions.CannotGrade;
import ee.taltech.iti0202.university.student.Student;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private List<Course> courses;
    private List<Student> allStudents;
    private List<Student> studyingStudents;

    /**
     * Creates a new university
     *
     * @param name university name
     */
    public University(String name) {
        this.name = name;
        courses = new ArrayList<>();
        allStudents = new ArrayList<>();
    }

    /**
     * Enrolls student to university if student isn't already enrolled to one
     *
     * @param student Student to be enrolled
     * @throws CannotAddStudent Why can't enroll
     */
    public void addStudent(Student student) throws CannotAddStudent {
        if (!isStudentAlreadyInSomeUni(student)) {
            allStudents.add(student);
            student.setUniversity(this);
        } else {
            throw new CannotAddStudent(CannotAddStudent.Reason.ALREADY_IN_UNI);
        }
    }

    /**
     * Add course to university courses if course doesn't have a uni assigned
     *
     * @param course Course to be added
     * @throws CannotAddCourse Error when already has a uni assigned
     */
    public void addCourse(Course course) throws CannotAddCourse {
        if (!isCourseInSomeUni(course)) {
            courses.add(course);
            course.setUniversity(this);
        } else {
            throw new CannotAddCourse(CannotAddCourse.Reason.COURSE_ALREADY_HAS_UNI_ASSIGNED);
        }

    }


    /**
     * If the student and course are in the same uni then adds grade to student
     * Otherwise throws cannot enroll exception
     *
     * @param student Student who got the grade
     * @param course  Course where the grade was gotten
     * @param grade   Grade that the student got
     * @throws CannotGrade When
     */
    public void addGrade(Student student, Course course, String grade) throws CannotGrade {
        if (areInTheSameUni(student, course)) {
            if (isStudentEnrolledToClass(student, course)) {
                student.addGrade(course, grade);
            } else {
                throw new CannotGrade(CannotGrade.Reason.STUDENT_IS_NOT_ENROLLED);
            }
        } else {
            throw new CannotGrade(CannotGrade.Reason.STUDENT_AND_COURSE_NOT_IN_THE_SAME_UNI);
        }

    }


    /**
     * Enrolls student to course when the they're both in the same uni and students isn't already enrolled in course
     *
     * @param student student to be enrolled
     * @param course  course that student wants to enroll
     */
    public void addStudentToCourse(Student student, Course course) throws CannotAddCourse {
        if (areInTheSameUni(student, course)) {
            student.enrollToCourse(course);
        } else {
            throw new CannotAddCourse(CannotAddCourse.Reason.STUDENT_AND_COURSE_IN_DIFFERENT_UNIS);
        }

    }

    /**
     * Checks if student is enrolled to a university
     *
     * @param student Student to check
     * @return if student is enrolled to any university
     */
    private boolean isStudentAlreadyInSomeUni(Student student) {
        return student.getUniversity() != null;
    }

    /**
     * Checks if course has an university already assigned to it
     *
     * @param course Course to check
     * @return if course has an university
     */
    private boolean isCourseInSomeUni(Course course) {
        return course.getUniversity() != null;
    }

    /**
     * Checks if the student and the course are in the same uni then return true, otherwise false
     *
     * @param student student to be checked
     * @param course  course to be checked
     * @return If the student and the course are in the same uni
     */
    private boolean areInTheSameUni(Student student, Course course) {
        return student.getUniversity().equals(course.getUniversity()) && student.getUniversity() != null;
    }

    /**
     * Checks if the student is enrolled to the course
     *
     * @param student student to be checked
     * @param course  course to be checked
     * @return If the student is enrolled to the course
     */
    private boolean isStudentEnrolledToClass(Student student, Course course) {
        return student.getOngoingCourses().contains(course);
    }

}

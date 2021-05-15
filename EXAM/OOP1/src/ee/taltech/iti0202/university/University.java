package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.declarationstrategy.DeclarationStrategy;
import ee.taltech.iti0202.university.exceptions.CannotAddCourseException;
import ee.taltech.iti0202.university.exceptions.CannotAddStudentException;
import ee.taltech.iti0202.university.exceptions.CannotDeclareException;
import ee.taltech.iti0202.university.exceptions.CannotGradeException;
import ee.taltech.iti0202.university.student.Student;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private List<Course> courses;
    private List<Student> allStudents;
    private List<Student> studyingStudents;
    private int minCreditPoints;
    private int maxCreditPoints;

    /**
     * Creates a new university
     *
     * @param name university name
     */
    public University(String name, int minCreditPoints, int maxCreditPoints) {
        this.name = name;
        courses = new ArrayList<>();
        allStudents = new ArrayList<>();
        studyingStudents = new ArrayList<>();
        this.minCreditPoints = minCreditPoints;
        this.maxCreditPoints = maxCreditPoints;
    }

    /**
     * Enrolls student to university if student isn't already enrolled to one
     *
     * @param student Student to be enrolled
     * @throws CannotAddStudentException Why can't enroll
     */
    public void addStudent(Student student) throws CannotAddStudentException {
        if (!isStudentAlreadyInSomeUni(student)) {
            allStudents.add(student);
            student.setUniversity(this);
        } else {
            throw new CannotAddStudentException(CannotAddStudentException.Reason.ALREADY_IN_UNI);
        }
    }

    /**
     * Declares courses for student and gets courses from strategy
     * Student can declare course only when student doesn't have any ongoing courses
     *
     * @param student student who want to declare courses
     */
    public void declareCourses(Student student) throws CannotDeclareException {
        if (student.getUniversity() != null) {
            if (student.getUniversity().equals(this)) {
                if (student.getOngoingCourses().isEmpty()) {
                    DeclarationStrategy declarationStrategy = student.getStrategy();
                    student.setOngoingCourses(declarationStrategy.getCourses());
                    studyingStudents.add(student);
                }
            } else {
                throw new CannotDeclareException(CannotDeclareException.Reason.WRONG_UNI);
            }
        } else {
            throw new CannotDeclareException(CannotDeclareException.Reason.STUDENT_NOT_ENROLLED_IN_UNI);
        }
    }

    /**
     * Checks if the study programme is completed by comparing students passed courses and the programme course list
     *
     * @param student student to be checked
     * @return if the study programme is completed
     */
    private boolean isStudyProgrammeCompleted(Student student) {
        return student.getStudyProgramme().getCourseList().equals(student.getPassedCourses());
    }


    /**
     * Add course to university courses if course doesn't have a uni assigned
     *
     * @param course Course to be added
     * @throws CannotAddCourseException Error when already has a uni assigned
     */
    public void addCourse(Course course) throws CannotAddCourseException {
        if (!isCourseInSomeUni(course)) {
            courses.add(course);
            course.setUniversity(this);
        } else {
            throw new CannotAddCourseException(CannotAddCourseException.Reason.COURSE_ALREADY_HAS_UNI_ASSIGNED);
        }

    }


    /**
     * If the student and course are in the same uni then adds grade to student
     * Later removes the student from studying students list if no more active courses
     * Otherwise throws cannot enroll exception
     *
     * @param student Student who got the grade
     * @param course  Course where the grade was gotten
     * @param grade   Grade that the student got
     * @throws CannotGradeException When
     */
    public void addGrade(Student student, Course course, String grade) throws CannotGradeException {
        if (areBothInUni(student, course)) {
            if (areInTheSameUni(student, course)) {
                if (isStudentEnrolledToClass(student, course)) {
                    student.addGrade(course, grade);
                    if (student.getOngoingCourses().isEmpty()) {
                        studyingStudents.remove(student);
                    }
                } else {
                    throw new CannotGradeException(CannotGradeException.Reason.STUDENT_IS_NOT_ENROLLED);
                }
            } else {
                throw new CannotGradeException(CannotGradeException.Reason.STUDENT_AND_COURSE_NOT_IN_THE_SAME_UNI);
            }
        } else {
            throw new CannotGradeException(CannotGradeException.Reason.STUDENT_IS_NOT_ENROLLED);
        }

    }


    /**
     * Enrolls student to course when the they're both in the same uni and students isn't already enrolled in course
     *
     * @param student student to be enrolled
     * @param course  course that student wants to enroll
     */
    public void addStudentToCourse(Student student, Course course) throws CannotAddCourseException {
        if (areBothInUni(student, course) && areInTheSameUni(student, course)) {
            student.enrollToCourse(course);
            studyingStudents.add(student);
        } else {
            throw new CannotAddCourseException(CannotAddCourseException.Reason.STUDENT_AND_COURSE_IN_DIFFERENT_UNIS);
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
        return student.getUniversity().equals(this)
                && course.getUniversity().equals(this);
    }

    private boolean areBothInUni(Student student, Course course) {
        return student.getUniversity() != null && course.getUniversity() != null;
    }

    /**
     * Checks if all the courses match the student uni
     *
     * @param student    student to be checked
     * @param courseList courses to check
     * @return if are all same or not
     */
    private boolean areAllCoursesInTheSameUni(Student student, List<Course> courseList) {
        for (Course course : courseList
        ) {
            if (!areInTheSameUni(student, course)) {
                return false;
            }
        }
        return true;
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

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

    public List<Student> getStudyingStudents() {
        return studyingStudents;
    }

    public int getMinCreditPoints() {
        return minCreditPoints;
    }

    public int getMaxCreditPoints() {
        return maxCreditPoints;
    }

    public void setMinCreditPoints(int minCreditPoints) {
        this.minCreditPoints = minCreditPoints;
    }
}

package ee.taltech.iti0202.university.student;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exceptions.CannotAddCourse;
import ee.taltech.iti0202.university.exceptions.CannotAddStudent;
import ee.taltech.iti0202.university.exceptions.CannotGrade;

import java.util.*;

public class Student {
    private String name;
    private int age;
    private Map<Course, List<String>> grades;
    private University university;
    private boolean isStudying;
    private List<Course> ongoingCourses;
    private List<Course> courseHistory;
    private List<Course> passedCourses;
    private List<Course> failedCourses;

    /**
     * Creates a new student when the student is older than 18
     *
     * @param name Student name
     * @param age  Student age
     * @throws CannotAddStudent Why cannot add student
     */
    public Student(String name, int age) throws CannotAddStudent {
        this.name = name;
        if (age > 18) {
            this.age = age;
        } else {
            throw new CannotAddStudent(CannotAddStudent.Reason.TOO_YOUNG);
        }
        grades = new HashMap<>();
        isStudying = false;
        ongoingCourses = new ArrayList<>();
        courseHistory = new ArrayList<>();
    }


    /**
     * Enrolls student to course by adding it to the list of ongoing courses
     * if not already in there and then adds it to the history.
     *
     * @param course course to be enrolled
     * @throws CannotAddCourse when already enrolled to the course then throws this error
     */
    public void enrollToCourse(Course course) throws CannotAddCourse {
        if (ongoingCourses.contains(course)) {
            throw new CannotAddCourse(CannotAddCourse.Reason.ALREADY_ENROLLED_TO_COURSE);
        } else {
            ongoingCourses.add(course);
            courseHistory.add(course);
        }
    }

    /**
     * If the course is graded meaning that has grades in between 0-5
     *
     * @param course
     * @param grade
     */
    public void addGrade(Course course, String grade) throws CannotGrade {
        if (doesCourseGradeMatch(course, grade).equals("num")) {
            if (grade.equals("0")) {
                failedCourses.add(course);
            } else {
                passedCourses.add(course);
            }
            ongoingCourses.remove(course);
            addGradeToMap(course, grade);
        } else if (doesCourseGradeMatch(course, grade).equals("pas")) {
            if (grade.equals("pass")) {
                failedCourses.add(course);
            } else {
                passedCourses.add(course);
            }
            ongoingCourses.remove(course);
            addGradeToMap(course, grade);
        }
    }

    public void addGradeToMap(Course course, String grade) {
        List<String> gradeList;
        if (grades.containsKey(course)) {
            gradeList = grades.get(course);
        } else {
            gradeList = new ArrayList<>();
        }
        gradeList.add(grade);
        grades.put(course, gradeList);
    }

    public String doesCourseGradeMatch(Course course, String grade) throws CannotGrade {
        if (course.isGraded()) {
            List<String> grades = Arrays.asList("0", "1", "2", "3", "4", "5");
            if (grades.contains(grade)) {
                return "num";
            } else {
                throw new CannotGrade(CannotGrade.Reason.WRONG_GRADE);
            }
        } else {
            List<String> grades = Arrays.asList("pass", "fail");
            if (grades.contains(grade)) {
                return "pas";
            } else {
                throw new CannotGrade(CannotGrade.Reason.WRONG_GRADE);
            }
        }
    }

    /**
     * Unroll from course
     *
     * @param course course to be unrolled from
     */
    public void unrollFromCourse(Course course) {
        if (ongoingCourses.contains(course)) {
            ongoingCourses.remove(course);
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Map<Course, List<String>> getGrades() {
        return grades;
    }

    public University getUniversity() {
        return university;
    }

    public boolean isStudying() {
        return isStudying;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Course> getCourseHistory() {
        return courseHistory;
    }

    public List<Course> getOngoingCourses() {
        return ongoingCourses;
    }
}

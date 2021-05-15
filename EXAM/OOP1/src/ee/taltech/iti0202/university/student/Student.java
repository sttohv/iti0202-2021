package ee.taltech.iti0202.university.student;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exceptions.CannotAddCourseException;
import ee.taltech.iti0202.university.exceptions.CannotAddStudentException;
import ee.taltech.iti0202.university.exceptions.CannotGradeException;
import ee.taltech.iti0202.university.declarationstrategy.DeclarationStrategy;
import ee.taltech.iti0202.university.studyprogramme.StudyProgramme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    public static final int APPROPRIATE_AGE = 18;
    private String name;
    private int age;
    private Map<Course, List<String>> grades;
    private University university;
    private boolean isStudying;
    private List<Course> ongoingCourses;
    private List<Course> courseHistory;
    private List<Course> passedCourses;
    private List<Course> failedCourses;
    private StudyProgramme studyProgramme;
    private DeclarationStrategy strategy;

    /**
     * Creates a new student when the student is older than 18
     *
     * @param name Student name
     * @param age  Student age
     * @throws CannotAddStudentException Why cannot add student
     */
    public Student(String name, int age, StudyProgramme studyProgramme) throws CannotAddStudentException {
        this.name = name;
        if (age > APPROPRIATE_AGE) {
            this.age = age;
        } else {
            throw new CannotAddStudentException(CannotAddStudentException.Reason.TOO_YOUNG);
        }
        grades = new HashMap<>();
        isStudying = false;
        ongoingCourses = new ArrayList<>();
        courseHistory = new ArrayList<>();
        this.studyProgramme = studyProgramme;
        strategy = null;
    }


    /**
     * Enrolls student to course by adding it to the list of ongoing courses
     * if not already in there and then adds it to the history.
     *
     * @param course course to be enrolled
     * @throws CannotAddCourseException when already enrolled to the course then throws this error
     */
    public void enrollToCourse(Course course) throws CannotAddCourseException {
        if (ongoingCourses.contains(course)) {
            throw new CannotAddCourseException(CannotAddCourseException.Reason.ALREADY_ENROLLED_TO_COURSE);
        } else {
            ongoingCourses.add(course);
            courseHistory.add(course);
        }
    }

    /**
     * Adds grades to correct courses list and then removes the from ongoing courses.
     *
     * @param course course
     * @param grade  grade
     */
    public void addGrade(Course course, String grade) throws CannotGradeException {
        if (doesCourseGradeMatch(course, grade).equals("num") || doesCourseGradeMatch(course, grade).equals("pas")) {
            if (grade.equals("0") || grade.equals("pass")) {
                failedCourses.add(course);
            } else {
                passedCourses.add(course);
            }
            ongoingCourses.remove(course);
            addGradeToMap(course, grade);
        }
    }

    /**
     * Adds grade to map depending on whether the student has gotten a grade there before or not
     *
     * @param course course
     * @param grade  grade
     */
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

    /**
     * Checks if the grade matches the course grading type and then returns the type of grade
     *
     * @param course course
     * @param grade  grade
     * @return grade type
     * @throws CannotGradeException if wrong grade type entered
     */
    public String doesCourseGradeMatch(Course course, String grade) throws CannotGradeException {
        if (course.isGraded()) {
            List<String> grades = Arrays.asList("0", "1", "2", "3", "4", "5");
            if (grades.contains(grade)) {
                return "num";
            } else {
                throw new CannotGradeException(CannotGradeException.Reason.WRONG_GRADE);
            }
        } else {
            List<String> grades = Arrays.asList("pass", "fail");
            if (grades.contains(grade)) {
                return "pas";
            } else {
                throw new CannotGradeException(CannotGradeException.Reason.WRONG_GRADE);
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

    public List<Course> coursesLeftToTakeFromProgramme(){
        List<Course> coursesLeft = new ArrayList<>();
        for (Course course:studyProgramme.getCourseList()
             ) {
            if(!passedCourses.contains(course)){
                coursesLeft.add(course);
            }
        }
        return coursesLeft;
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

    public StudyProgramme getStudyProgramme() {
        return studyProgramme;
    }

    public void setStrategy(DeclarationStrategy declarationStrategy) {
        this.strategy = declarationStrategy;
    }

    public List<Course> getPassedCourses() {
        return passedCourses;
    }

    public DeclarationStrategy getStrategy() {
        return strategy;
    }

    public void setOngoingCourses(List<Course> ongoingCourses) {
        this.ongoingCourses = ongoingCourses;
    }
}

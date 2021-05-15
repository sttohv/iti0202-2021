package ee.taltech.iti0202.university.declarationstrategy;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.exceptions.CannotDeclareException;
import ee.taltech.iti0202.university.student.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EasierCoursesFirstStrategy implements DeclarationStrategy {

    private List<Course> courseList;
    private Student student;

    /**
     * Creates a new strategy that takes in courses to be declared and university to declare
     * tudeng valib need sooritamata ained õppekavast, mis annavad vähem
     * ainepunkte ning mis tal on tegemata (st tulemus ei ole positiivne).
     * Sealjuures on määratud minimaalne ja maksimaalne deklaratsiooni
     * ainepunktide arv - tudeng proovib täita minimaalse nõude.
     *
     * @param student student who wants to declare courses
     */
    public EasierCoursesFirstStrategy(Student student) {
        this.courseList = sortCourses(student.getCoursesLeftToTakeFromProgramme());
        this.student = student;
    }

    @Override
    public List<Course> getCourses() throws CannotDeclareException {
        List<Course> courses = new ArrayList<>();
        for (Course course : courseList
        ) {
            if (isDeclaredEnoughCreditPoints(courses, student.getUniversity())) {
                return courses;
            } else {
                if ((getSumOfCreditPoints(courses) + course.getCreditPoints()) <= student.getUniversity()
                        .getMaxCreditPoints()) {
                    courses.add(course);
                } else {
                    throw new CannotDeclareException(CannotDeclareException.Reason.NO_SUCH_COURSES);
                }
            }
        }
        return courses;
    }

    /**
     * Checks if chosen courses have enough credit points
     *
     * @param courseList course to be checked
     * @param university university to get min points from
     * @return if chosen courses have enough credit points
     */
    private boolean isDeclaredEnoughCreditPoints(List<Course> courseList, University university) {
        int sum = getSumOfCreditPoints(courseList);
        return sum >= university.getMinCreditPoints();
    }

    /**
     * Sorts courses by credit points from smaller to bigger
     *
     * @param courses courses to sort
     * @return list of courses
     */
    private List<Course> sortCourses(List<Course> courses) {
        return courses
                .stream()
                .sorted(Comparator.comparingInt(Course::getCreditPoints))
                .collect(Collectors.toList());
    }

    /**
     * Adds up all the credit points from entered courses
     *
     * @param courses courses to take credit points
     * @return sum of credit points
     */
    private int getSumOfCreditPoints(List<Course> courses) {
        int sum = 0;
        for (Course course : courses
        ) {
            sum += course.getCreditPoints();
        }
        return sum;
    }


}

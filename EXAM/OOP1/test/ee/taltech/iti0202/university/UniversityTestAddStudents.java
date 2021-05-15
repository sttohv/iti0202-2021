package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.exceptions.CannotAddStudentException;
import ee.taltech.iti0202.university.student.Student;
import ee.taltech.iti0202.university.studyprogramme.StudyProgramme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class UniversityTestAddStudents {

    public static final int STINA_AGE = 20;
    public static final int ERGO_AGE = 10;

    @Test
    public void addTooYoungStudentToUni() {
        University ttu = new University("ttu", 6, 60);
        StudyProgramme developement = new StudyProgramme("developement", List.of(), ttu);
        CannotAddStudentException.Reason reason = null;
        try {
            Student ergo = new Student("Ergo", ERGO_AGE, developement);
        } catch (CannotAddStudentException e) {
            reason = e.getReason();
        }
        Assertions.assertEquals(CannotAddStudentException.Reason.TOO_YOUNG, reason);
    }

    @Test
    public void addStudentCorrect() throws CannotAddStudentException {
        University ttu = new University("ttu", 6, 60);
        StudyProgramme businessIt = new StudyProgramme("businessIt", List.of(), ttu);
        Student stina = new Student("Stina", STINA_AGE, businessIt);
        University talTech = new University("TalTech", 6, 60);

        talTech.addStudent(stina);

        Assertions.assertEquals(talTech.getAllStudents().size(), 1);
        Assertions.assertEquals(talTech.getStudyingStudents().size(), 0);

    }

    @Test
    public void addStudentAlreadyInUni() throws CannotAddStudentException {
        University ttu = new University("ttu", 6, 60);
        StudyProgramme businessIt = new StudyProgramme("businessIt", List.of(), ttu);
        CannotAddStudentException.Reason reason = null;

        Student stina = new Student("Stina", STINA_AGE, businessIt);
        University talTech = new University("TalTech", 6, 60);
        talTech.addStudent(stina);

        try {
            talTech.addStudent(stina);
        } catch (CannotAddStudentException e) {
            reason = e.getReason();
        }
        Assertions.assertEquals(CannotAddStudentException.Reason.ALREADY_IN_UNI, reason);

    }
}

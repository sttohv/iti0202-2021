package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.exceptions.CannotAddStudent;
import ee.taltech.iti0202.university.student.Student;
import ee.taltech.iti0202.university.studyprogramme.StudyProgramme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class UniversityTest {

    @Test
    public void addTooYoungStudentToUni(){
        StudyProgramme developement = new StudyProgramme("developement", List.of());
        CannotAddStudent.Reason reason = null;
        try {
            Student ergo = new Student("Ergo", 10, developement);
        }catch (CannotAddStudent e){
            reason = e.getReason();
        }
        Assertions.assertEquals(CannotAddStudent.Reason.TOO_YOUNG, reason);
    }

    @Test
    public void addStudentCorrect() throws CannotAddStudent {
        StudyProgramme businessIt = new StudyProgramme("businessIt", List.of());
        Student Stina = new Student("Stina", 20, businessIt);
        University TalTech = new University("TalTech");

        TalTech.addStudent(Stina);

        Assertions.assertEquals(TalTech.getAllStudents().size(), 1);
        Assertions.assertEquals(TalTech.getStudyingStudents().size(), 0);

    }

    @Test
    public void addStudentAlreadyInUni() throws CannotAddStudent {
        StudyProgramme businessIt = new StudyProgramme("businessIt", List.of());
        CannotAddStudent.Reason reason = null;

        Student Stina = new Student("Stina", 20, businessIt);
        University TalTech = new University("TalTech");
        TalTech.addStudent(Stina);

        try{
        TalTech.addStudent(Stina);}
        catch (CannotAddStudent e){
            reason = e.getReason();
        }
        Assertions.assertEquals(CannotAddStudent.Reason.ALREADY_IN_UNI, reason);

    }


}
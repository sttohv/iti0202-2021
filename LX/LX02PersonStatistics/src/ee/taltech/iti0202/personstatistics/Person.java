package ee.taltech.iti0202.personstatistics;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private double heightInMeters;
    private String occupation;
    private String nationality;

    /**
     * Person
     *
     * @param firstName      first name
     * @param lastName       last name
     * @param age            age
     * @param gender         gender
     * @param heightInMeters height
     * @param occupation     occupation
     * @param nationality    nationality
     */
    public Person(String firstName, String lastName, int age, Gender gender, double heightInMeters,
                  String occupation, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.heightInMeters = heightInMeters;
        this.occupation = occupation;
        this.nationality = nationality;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public int getLastNameLength() {
        return lastName.length();
    }

    public String getNationality() {
        return nationality;
    }

    public double getHeightInMeters() {
        return heightInMeters;
    }

    public Gender getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getOccupation() {
        return occupation;
    }
}

package ee.taltech.iti0202.personstatistics;

public class PersonBuilder {
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private double heightInMeters;
    private String occupation;
    private String nationality;

    public PersonBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public PersonBuilder setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public PersonBuilder setHeightInMeters(double heightInMeters) {
        this.heightInMeters = heightInMeters;
        return this;
    }

    public PersonBuilder setOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public PersonBuilder setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    /**
     * creates a person
     * @return person
     */
    public Person createPerson() {
        return new Person(firstName, lastName, age, gender, heightInMeters, occupation, nationality);
    }
}

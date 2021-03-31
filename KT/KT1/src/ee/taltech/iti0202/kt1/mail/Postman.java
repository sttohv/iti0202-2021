package ee.taltech.iti0202.kt1.mail;

import java.util.ArrayList;
import java.util.List;

public class Postman {
    public static final int OLD_AGE = 40;
    private String name;
    private Integer age;
    private List<Letter> letters;

    /**
     * Create a postman with the name and the age.
     */
    public Postman(String name, Integer age) {
        this.name = name;
        this.age = age;
        letters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    /**
     * Adds a letter to postman.
     * The letter can be added if the name of the postman and the name of the letter's address
     * start with the same symbol.
     * Also, each postman has a letter limit.
     * If the age of the postman is 40 or larger, then the limit of the letters is: age - name length
     * If the age of the postman is below 40, the limit is age + name length.
     * If the first letters do not match or the letter limit is reached, returns false.
     * Otherwise returns true and letter is added to postman.
     */
    public boolean addLetter(Letter letter) {
        if (letter.getAddress().substring(0, 1).equals(name.substring(0, 1))) {
            if (age >= OLD_AGE && letters.size() < (age - name.length())) {
                letters.add(letter);
                return true;
            } else if (age < OLD_AGE && letters.size() < age + name.length()) {
                letters.add(letter);
                return true;
            }
        }
        return false;
    }
}

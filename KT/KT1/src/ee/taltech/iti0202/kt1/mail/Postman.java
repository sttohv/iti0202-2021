package ee.taltech.iti0202.kt1.mail;

import java.util.ArrayList;
import java.util.List;

public class Postman {
    private String name;
    private Integer age;
    private List<Letter> letters;

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

    public boolean addLetter(Letter letter) {
        if (letter.getAddress().substring(0, 1).equals(name.substring(0, 1))) {
            if (age >= 40 && letters.size() < (age - name.length())) {
                letters.add(letter);
                return true;
            } else if (age < 40 && letters.size() < age + name.length()) {
                letters.add(letter);
                return true;
            }
        }
        return false;
    }
}

package ee.taltech.iti0202.kt1.mail;

import java.util.ArrayList;
import java.util.List;

public class PostOffice {
    private String location;
    private List<Letter> letters;
    private List<Postman> postmen;

    /**
     * Create a post office with the location.
     */
    public PostOffice(String location) {
        this.location = location;
        letters = new ArrayList<>();
        postmen = new ArrayList<>();
    }

    /**
     * Adds a letter to the post office.
     * Letter is added only if the letter's destination city matches the location of the office.
     */
    public void addLetter(Letter letter) {
        if (letter.getDestinationCity().equals(location)) {
            letters.add(letter);
        }
    }

    /**
     * Adds a postman to the office.
     * If there is a postman with the same first letter already in the office,
     * then this postman is not added.
     * Otherwise postman is added to the office.
     */
    public void addPostman(Postman postman) {
        boolean sameName = false;
        for (Postman man : postmen
        ) {
            if (man.getName().substring(0, 1).equals(postman.getName().substring(0, 1))) {
                sameName = true;
            }
        }
        if (!sameName) {
            postmen.add(postman);
        }
    }

    /**
     * Returns all the letter in the post office.
     */
    public List<Letter> getAllLetters() {
        return letters;
    }

    /**
     * Returns all the postmen in the office.
     */
    public List<Postman> getPostmen() {
        return postmen;
    }

    /**
     * Divide letter in the office to postmen.
     * The division algorithm is as follows:
     * Each letter is assigned to each postman.
     * If the postman can take this letter (addLetter method), then this letter
     * is added to the postman and removed from the office.
     * If no postman can take the letter, then this letter remains in the office.
     */
    public void divideLetters() {
        List<Letter> newLetters = new ArrayList<>(letters);
        for (Letter letter : letters
        ) {
            for (Postman postman : postmen
            ) {
                if (postman.addLetter(letter)) {
                    newLetters.remove(letter);
                }
            }
        }
        letters = newLetters;
    }

    public static void main(String[] args) {
        PostOffice postOffice = new PostOffice("Tallinn");

        Postman postman = new Postman("Martin", 40);

        postOffice.addPostman(postman);

        postOffice.addLetter(new Letter("Toomas", "Tartu", "Rahu tn"));
        postOffice.addLetter(new Letter("Erki", "Tallinn", "Männi tee"));

        postOffice.divideLetters();

        System.out.println(postman.getLetters());   // [City: Tallinn, Address: Männi tee, Recipient: Erki]
    }
}

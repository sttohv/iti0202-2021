package ee.taltech.iti0202.bookshelf;

import java.util.List;

/**
 * ekjfckje
 */
public class Main {


    public static final int YEAR_OF_PUBLISHING = 2001;
    public static final int PRICE = 101;
    public static final int YEAR_OF_PUBLISHING1 = 2002;
    public static final int PRICE1 = 102;
    public static final int YEAR_OF_PUBLISHING2 = 2001;
    public static final int PRICE2 = 101;
    public static final int PRICE3 = 103;

    /**
     * efcnvjn
     *
     * @param args jdnfvj
     */
    public static void main(String[] args) {
        Book b1 = Book.of("testOfLastAdded1", "testOfLastAddedAuthor1", YEAR_OF_PUBLISHING, PRICE);
        Book b2 = Book.of("testOfLastAdded2", "testOfLastAddedAuthor2", YEAR_OF_PUBLISHING1, PRICE1);
        Book b3 = Book.of("testOfLastAdded1", "testOfLastAddedAuthor1", YEAR_OF_PUBLISHING2, PRICE2);
        System.out.println(b1.equals(b3));
        Book b4 = Book.of("testOfLastAdded3", PRICE3);
        System.out.println(b4.getAuthor());

    }
}

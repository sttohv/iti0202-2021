package ee.taltech.iti0202.bookshelf;

import java.util.List;

/**
 * ekjfckje
 */
public class Main {




    /**
     * efcnvjn
     *
     * @param args jdnfvj
     */
    public static void main(String[] args) {
        Book b1 = Book.of("testOfLastAfterRemove", "testOfLastAfterRemoveAuthor", 2000, 100);
        Book b2 = Book.of("testOfLastAfterRemove2", "testOfLastAfterRemoveAuthor2", 2002, 102);
        Book.removeBook(b2);
        for (Book book:Book.getAllOfBooks()
             ) {
            System.out.println(book.getAuthor());
        }

        Book b3 = Book.of("testOfLastAfterRemove3", 101);
        System.out.println(b3.getTitle().equals("testOfLastAfterRemove3"));
        System.out.println(b3.getAuthor().equals("testOfLastAfterRemoveAuthor2"));
//        assertEquals(b3.getYearOfPublishing(), 2002);
        System.out.println(b3.getPrice()== 101);

    }
}

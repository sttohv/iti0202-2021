package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String bookTitle;
    private String bookAuthor;
    private int bookYear;
    private int bookPrice;
    private Person bookOwner;
    private static int idCount = -1;
    private int bookId;
    private static List<Book> allBooks = new ArrayList<>();

    public static int getAndIncrementNextId() {
        idCount++;
        return idCount;
    }

    public Book(String title, String author, int yearOfPublishing, int price) {
        bookTitle = title;
        bookAuthor = author;
        bookYear = yearOfPublishing;
        bookPrice = price;
        bookId = getAndIncrementNextId();

    }

    public String getTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return bookAuthor;
    }

    public int getYearOfPublishing() {
        return bookYear;
    }

    public Person getOwner() {
        return bookOwner;
    }

    public int getPrice() {
        return bookPrice;
    }

    public int getId() {
        return idCount;
    }

    public void setBookOwner(Person bookOwner) {
        this.bookOwner = bookOwner;
    }

    public boolean buy(Person buyer) {
        if (bookOwner.equals(buyer) || buyer.buyBook(this)) {
            return false; // ei õnnestunud - praegune omanik, kui raha pole(kontrollib buyBook),
        } else {
            if (buyer == null) {
                Person stina = getOwner();
                getOwner().setPersonMoney(5);
            }
            buyer.setPersonMoney(buyer.getMoney() - getPrice());
            setBookOwner(buyer);
            return true; //õnnestus -
        }


    }
}
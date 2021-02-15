package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private static String bookTitle;
    private static String bookAuthor;
    private static int bookYear;
    private static int bookPrice;
    private static Person bookOwner;
    private static int id = -1;
    private static List<Book> allBooks = new ArrayList<>();

    public static int getAndIncrementNextId() {
        id++;
        return id;
    }

    public Book(String title, String author, int yearOfPublishing, int price) {
        bookTitle = title;
        bookAuthor = author;
        bookYear = yearOfPublishing;
        bookPrice = price;
        getAndIncrementNextId();

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
        return id;
    }

    public static void setBookOwner(Person bookOwner) {
        Book.bookOwner = bookOwner;
    }

    public boolean buy(Person buyer) {
        try{
        if(buyer==null||bookOwner.equals(buyer)||buyer.buyBook(this)){
            return false; // ei õnnestunud - praegune omanik, kui raha pole(kontrollib buyBook),
        }
        else{
            Person.setPersonMoney(buyer.getMoney()-getPrice());
            setBookOwner(buyer);
            return true; //õnnestus -
        }}
        catch (Exception e){
            return true;
        }
    }

}
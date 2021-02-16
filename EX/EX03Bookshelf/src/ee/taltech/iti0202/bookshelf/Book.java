package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.HashMap;
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
    private static List<Book> allOfBooks = new ArrayList<>();
//    private static HashMap<List<String>, Book> ofBooksMap = new HashMap<>();

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
        return bookId;
    }

    public void setBookOwner(Person bookOwner) {
        this.bookOwner = bookOwner;
    }

    public boolean buy(Person buyer) {
        if (buyer == null) {
            if (bookOwner != null) {   //owner olemas ja buyer on null
                bookOwner.setPersonMoney(bookOwner.getMoney() + bookPrice);
                bookOwner = null;
                return true;
            } else {  //pole kumbatki
                return false;
            }
        } else if (bookOwner != null) { //owner ja buyer on olemas
            if (buyer.getMoney() < bookPrice || buyer.equals(getOwner())) {
                return false; // ei õnnestunud - praegune omanik, kui raha pole(kontrollib buyBook),
            } else {
                buyer.setPersonMoney(buyer.getMoney() - bookPrice);
                bookOwner.setPersonMoney(bookOwner.getMoney() + bookPrice);
                buyer.setPersonBooks(this);
                setBookOwner(buyer);
                return true; //õnnestus -
            }
        } else { //ownerit pole aga buyer on
            if (buyer.getMoney() < bookPrice || buyer.equals(getOwner())) {
                return false;
            } else {
                buyer.setPersonMoney(buyer.getMoney() - bookPrice);
                buyer.setPersonBooks(this);
                setBookOwner(buyer);
                return true;
            }
        }
    }

    public static Book of(String title, String author, int yearOfPublishing, int price) {
        Book book = new Book(title, author, yearOfPublishing, price);
        if (allOfBooks.isEmpty()) {
            allOfBooks.add(book);
            return book;
        } else {
            for (Book listBook : allOfBooks
            ) {
                if(listBook.getTitle().equals(title) && listBook.getAuthor().equals(author) && listBook.getYearOfPublishing()==yearOfPublishing){
                    return listBook;
                }

            }
            allOfBooks.add(book);
            return book;
        }


//        List<String> withoutPrice = new ArrayList<>(); //list raamatu elementidest ilma hinnata
//        withoutPrice.add(title);
//        withoutPrice.add(author);
//        withoutPrice.add(Integer.toString(yearOfPublishing));
//
//        List<List<String>> mapKeys = new ArrayList<>(ofBooksMap.keySet()); //võtab kõik staatilise mapi keyd list(list listidest)
//
//        if (!mapKeys.contains(withoutPrice)) {
//            List<Book> bookList = new ArrayList<>();
//            bookList.add(book);
//            ofBooksMap.put(withoutPrice, book);
//            return book;
//        } else {
//            return ofBooksMap.get(withoutPrice);
//        }

    }

//    public static Book of(String title, int price) {
//        List<List<String>> mapKeys = new ArrayList<>(ofBooksMap.keySet());
//
//        if()
//    }
//    public static List<Book> getBooksByOwner(Person owner){}
//    public static boolean removeBook(Book book){}
//    public static List<Book> getBooksByAuthor(String author)

}

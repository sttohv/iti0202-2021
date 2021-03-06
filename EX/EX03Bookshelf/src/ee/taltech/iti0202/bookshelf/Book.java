package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Constructor
 */
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
    private static Book lastBook;
    private static HashMap<List<String>, Book> ofBooksMap = new HashMap<>();

    /**
     * joujou
     *
     * @return idCOuntfsijc
     */
    public static int getAndIncrementNextId() {
        idCount++;
        return idCount;
    }

    /**
     * sadc
     *
     * @param title            fhj
     * @param author           djs
     * @param yearOfPublishing njf
     * @param price            df
     */
    public Book(String title, String author, int yearOfPublishing, int price) {
        bookTitle = title;
        bookAuthor = author;
        bookYear = yearOfPublishing;
        bookPrice = price;
        bookId = getAndIncrementNextId();

    }

    /**
     * jdc
     *
     * @return fdn
     */
    public String getTitle() {
        return bookTitle;
    }

    /**
     * djc
     *
     * @return jfdc
     */
    public String getAuthor() {
        try {
            return bookAuthor;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * jcnj
     *
     * @return jf
     */
    public int getYearOfPublishing() {
        return bookYear;
    }

    /**
     * dcc
     *
     * @return jnd
     */
    public Person getOwner() {
        return bookOwner;
    }

    /**
     * vjfvd
     *
     * @return kdmfv
     */
    public int getPrice() {
        return bookPrice;
    }

    /**
     * dfkv
     *
     * @return jnv
     */
    public int getId() {
        return bookId;
    }

    /**
     * jdfvnjdfn v
     *
     * @param bookOwner dfvdmnf
     */
    public void setBookOwner(Person bookOwner) {
        this.bookOwner = bookOwner;
    }

    /**
     * vn djfvjnd
     *
     * @param buyer vjndfv
     * @return jdnfvj
     */
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
                return false; // ei ??nnestunud - praegune omanik, kui raha pole(kontrollib buyBook),
            } else {
                buyer.setPersonMoney(buyer.getMoney() - bookPrice);
                bookOwner.setPersonMoney(bookOwner.getMoney() + bookPrice);
                buyer.setPersonBooks(this);
                setBookOwner(buyer);
                return true; //??nnestus -
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

    /**
     * dfn vjdnv
     *
     * @param title            jfv
     * @param author           vfcv
     * @param yearOfPublishing jdfv
     * @param price            jdvn
     * @return jdfvn
     */
    public static Book of(String title, String author, int yearOfPublishing, int price) {
        Book book = new Book(title, author, yearOfPublishing, price);
        if (allOfBooks.size() > 0) {
            for (Book listBook : allOfBooks
            ) {
                if (listBook.getTitle().equals(title) && listBook.getAuthor().equals(author)
                        && listBook.getYearOfPublishing() == yearOfPublishing && listBook.getPrice() == price) {
                    return listBook;
                }
                if (listBook.getTitle().equals(title) && listBook.getAuthor().equals(author)
                        && listBook.getYearOfPublishing() == yearOfPublishing) {
                    allOfBooks.add(listBook);
                    lastBook = listBook;
                    return listBook;
                }

            }
        }
        allOfBooks.add(book);
        lastBook = book;
        return book;
    }


//        List<String> withoutPrice = new ArrayList<>(); //list raamatu elementidest ilma hinnata
//        withoutPrice.add(title);
//        withoutPrice.add(author);
//        withoutPrice.add(Integer.toString(yearOfPublishing));
//
//        List<List<String>> mapKeys = new ArrayList<>(ofBooksMap.keySet());
//        //v??tab k??ik staatilise mapi keyd list(list listidest)
//
//        if (!mapKeys.contains(withoutPrice)) {
//            ofBooksMap.put(withoutPrice, book);
//            allOfBooks.add(book);
//            return book;
//        } else {
//            allOfBooks.add(ofBooksMap.get(withoutPrice));
//            return ofBooksMap.get(withoutPrice);
//        }

//    }

    /**
     * dmfv
     *
     * @param title dgb
     * @param price dvf
     * @return dfv
     */
    public static Book of(String title, int price) {
        if (lastBook != null) {
            Book newBook = new Book(title, lastBook.getAuthor(), lastBook.getYearOfPublishing(), price);
            allOfBooks.add(newBook);
            lastBook = newBook;
            return newBook;
        }
        return null;
    }

    /**
     * wjc
     *
     * @param owner dfvf
     * @return dfvdf
     */
    public static List<Book> getBooksByOwner(Person owner) {
        return owner.getBooks();
    }

    /**
     * fnf
     *
     * @param book jdfv
     * @return jdvd
     */
    public static boolean removeBook(Book book) {
        if (book != null && allOfBooks.contains(book)) {
            if (book.bookOwner != null) {
                book.bookOwner.setPersonMoney(book.bookOwner.getMoney() + book.bookPrice);
                book.bookOwner.removePersonBooks(book);
                book.setBookOwner(null);

            }
            allOfBooks.remove(book);
            return true;
        }
        return false;
    }

    /**
     * jfnjnf
     *
     * @param author jdfvh
     * @return jdfv
     */
    public static List<Book> getBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : allOfBooks
        ) {
            if (book.bookAuthor.toLowerCase(Locale.ROOT).equals(author.toLowerCase(Locale.ROOT))) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * jfi
     *
     * @return dsfhfs
     */
    public static List<Book> getAllOfBooks() {
        return allOfBooks;
    }
}

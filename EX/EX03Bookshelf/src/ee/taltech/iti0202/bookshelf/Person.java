package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;

/**
 * ejcfjn
 */
public class Person {
    private String personName;
    private int personMoney;
    private List<Book> personBooks;

    /**
     * dcdafv
     *
     * @param name  efcvdf
     * @param money kfmvkfdv
     */
    public Person(String name, int money) {
        personName = name;
        personMoney = money;
        personBooks = new ArrayList<>();
    }

    /**
     * jfnvkfv
     *
     * @return kmfckf
     */
    public int getMoney() {
        try {
            return personMoney;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * jfvnkjdfv
     *
     * @return dnfcvjndf
     */
    public String getName() {
        return personName;
    }

    /**
     * jdfncjn
     *
     * @param book jndfcjvn
     * @return jndfjnd
     */
    public boolean buyBook(Book book) {
        try {
            if (book == null || book.getPrice() > personMoney || book.getOwner() != null) {
                return false;
            } else {
                personBooks.add(book);
                book.setBookOwner(this);
                setPersonMoney(getMoney() - book.getPrice());
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * jefnjk
     *
     * @param book efcv
     * @return dfv
     */
    public boolean sellBook(Book book) {
        if (book == null || !personBooks.contains(book)) {
            return false;
        } else {
            personBooks.remove(book);
            setPersonMoney(getMoney() + book.getPrice());
            book.setBookOwner(null);
            return true;
        }
    }

    /**
     * befkhb
     *
     * @param personMoney efbcjhbef
     */
    public void setPersonMoney(int personMoney) {
        this.personMoney = personMoney;
    }

    /**
     * jfvnjd
     *
     * @param book inefcvjnef
     */
    public void setPersonBooks(Book book) {
        this.personBooks.add(book);
    }

    /**
     * itg
     *
     * @return lgblgkrj
     */
    public List<Book> getBooks() {
        return personBooks;
    }
}

package ee.taltech.iti0202.bookshelf;

import java.util.List;

/**
 * ekjfckje
 */
public class Main {

    public static final int YEAR_OF_PUBLISHING = 1926;
    public static final int PRICE = 100;
    public static final int YEAR_OF_PUBLISHING1 = 1976;
    public static final int PRICE1 = 200;
    public static final int MATIMONEY = 200;
    public static final int KATIMONEY = 300;
    public static final int JAVA03EXPRICE = 7;
    public static final int JAVAEX01PRICE = 11;
    public static final int YEAR_OF_PUBLISHING2 = 2018;
    public static final int YEAR_OF_PUBLISHING3 = 1997;
    public static final int YEAR_OF_PUBLISHING4 = 1998;
    public static final int PRICE3 = 1000;
    public static final int PRICE2 = 1000;
    public static final int MONEY = 10000;

    /**
     * efcnvjn
     *
     * @param args jdnfvj
     */
    public static void main(String[] args) {
        Book tammsaare = new Book("Truth and Justice", "Tammsaare", YEAR_OF_PUBLISHING, PRICE);
        Book meri = new Book("Silverwhite", "Meri", YEAR_OF_PUBLISHING1, PRICE1);

        Person mati = new Person("Mati", MATIMONEY);
        Person kati = new Person("Kati", KATIMONEY);

        System.out.println(mati.buyBook(tammsaare)); // true
        System.out.println(mati.getMoney());  // 100
        System.out.println(tammsaare.getOwner().getName()); // Mati
        System.out.println();

        System.out.println(mati.sellBook(tammsaare)); // true
        System.out.println(mati.getMoney()); // 200
        System.out.println(tammsaare.getOwner()); // null
        System.out.println();

        System.out.println(mati.sellBook(tammsaare)); // false
        System.out.println();

        System.out.println(mati.buyBook(meri)); // true
        System.out.println(mati.getMoney()); // 0
        System.out.println();

        System.out.println(meri.buy(kati)); // true
        System.out.println(mati.getMoney()); // 200
        System.out.println(kati.getMoney()); // 100
        System.out.println(meri.buy(kati)); // false
        System.out.println(kati.getMoney()); // 100
        System.out.println(meri.getOwner().getName()); // Kati
        System.out.println(kati.sellBook(meri)); // true
        System.out.println(meri.getOwner()); // null
        System.out.println();

        // id
        System.out.println(tammsaare.getId()); // 0
        System.out.println(meri.getId()); // 1
        System.out.println(Book.getAndIncrementNextId()); // 2
        System.out.println();

        // second part
        Book b0 = Book.of("Java EX00", 1);
        System.out.println(b0); // null
        Book b1 = Book.of("Java EX01", "Ago Luberg", YEAR_OF_PUBLISHING2, 3);
        Book b2 = Book.of("Java EX02", 4);
        System.out.println(b2.getAuthor()); // Ago Luberg
        Book b3 = Book.of("Java EX03", JAVA03EXPRICE);
        Book b4 = Book.of("Java EX01", JAVAEX01PRICE);
        System.out.println(b1 == b4); // true
        Book harry1 = Book.of("Harry Potter: The Philosopher's Stone", "J. K. rowling",
                YEAR_OF_PUBLISHING3, PRICE2);
        Book harry2 = Book.of("Harry Potter: The Chamber of Secrets", "J. K. Rowling",
                YEAR_OF_PUBLISHING4, PRICE3);
        List<Book> rowlingBooks = Book.getBooksByAuthor("j. k. rowling");
        System.out.println(rowlingBooks.size()); // 2
        System.out.println(rowlingBooks.get(0).getTitle()); // Harry Potter: The Philosopher's Stone
        System.out.println(rowlingBooks.get(1).getAuthor()); // J. K. Rowling

        Person bonusPerson = new Person("Joonas Boonus", MONEY);
        b1.buy(bonusPerson);
        bonusPerson.buyBook(harry1);

        List<Book> personBooks = Book.getBooksByOwner(bonusPerson);
        System.out.println(personBooks.size()); // 2
        System.out.println(personBooks.contains(b1));  // true
        System.out.println(personBooks.contains(harry1)); // true
        System.out.println(bonusPerson.getMoney()); // 8997

        Book.removeBook(b1);
        personBooks = Book.getBooksByOwner(bonusPerson);
        for (Book book : personBooks
        ) {
            System.out.println(book.getTitle());

        }
        System.out.println(personBooks.size()); // 1
        System.out.println(personBooks.contains(b1)); // false
        System.out.println(bonusPerson.getMoney()); // 9000

    }
}

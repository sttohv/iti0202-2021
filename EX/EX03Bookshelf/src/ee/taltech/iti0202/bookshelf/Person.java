package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private static String personName;
    private static int personMoney;
    private static List<Book> personBooks;

    public Person(String name, int money) {
        personName = name;
        personMoney = money;
        personBooks = new ArrayList<>();
    }

    public int getMoney() {
        return personMoney;
    }

    public String getName() {
        return personName;
    }

    public boolean buyBook(Book book) {
        if (book == null || book.getPrice() > getMoney() || book.getOwner() == null) {
            return false;
        } else {
            personBooks.add(book);
            //Book.setBookOwner(this);
            setPersonMoney(getMoney()- book.getPrice());
            return true;
        }
    }

    public boolean sellBook(Book book) {
        if(book==null||book.getOwner()!=this){
        return false;}
        else{
            setPersonMoney(getMoney()- book.getPrice());

            return true;
        }
    }

    public static void setPersonMoney(int personMoney) {
        Person.personMoney = personMoney;
    }
}
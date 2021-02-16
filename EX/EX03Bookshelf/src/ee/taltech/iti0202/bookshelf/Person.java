package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String personName;
    private int personMoney;
    private List<Book> personBooks;

    public Person(String name, int money) {
        personName = name;
        personMoney = money;
        personBooks = new ArrayList<>();
    }

    public int getMoney() {
        if(this == null || personMoney==0){
            return 0;
        }
        return personMoney;
    }

    public String getName() {
        return personName;
    }

    public boolean buyBook(Book book) {
        try{
        if (book == null || book.getPrice() > personMoney || book.getOwner() != null) {
            return false;
        } else {
            personBooks.add(book);
            book.setBookOwner(this);
            setPersonMoney(getMoney()- book.getPrice());
            return true;
        }}
        catch (Exception e){
            return false;
        }
    }

    public boolean sellBook(Book book) {
        if(book==null||!personBooks.contains(book)){
        return false;}
        else{
            personBooks.remove(book);
            setPersonMoney(getMoney()+ book.getPrice());
            book.setBookOwner(null);
            return true;
        }
    }

    public void setPersonMoney(int personMoney) {
        this.personMoney = personMoney;
    }

    public void setPersonBooks(Book book) {
        this.personBooks.add(book);
    }
}
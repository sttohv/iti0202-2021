package ee.taltech.iti0202.exam.order;

public class Product {
    private String name;
    private int price;

    /**
     * Constructor
     *
     * @param name  Product name
     * @param price Product price
     */
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

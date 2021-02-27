package ee.taltech.iti0202.tk2.shop;

public class Product {

    private String productName;
    private int productPrice;

    public Product(String name, int price) {
        if (!name.equals("")) {
            productName = name;
            productPrice = price;
        }
    }

    public Product(int price) {
        productPrice = price;
    }

    public int getPrice() {
        return productPrice;
    }

    public String getName() {
        return productName;
    }
    @Override
    public String toString() {
        if (productName.equals(null)) {
            return "(" + productPrice + ")";
        } else {
            return productName + " (" + productPrice + ")";
        }
    }
}
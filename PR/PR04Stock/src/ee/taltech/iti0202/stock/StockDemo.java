package ee.taltech.iti0202.stock;

import ee.taltech.iti0202.stock.exceptions.StockException;
import ee.taltech.iti0202.stock.product.Product;
import ee.taltech.iti0202.stock.stock.Stock;

import java.util.Optional;

public class StockDemo {

    public static void main(String[] args) throws StockException {
        Stock fruitStock = new Stock("fruit-stock-1", 4);

        Product cheapApple = new Product("apple", 3);
        Product expensiveApple = new Product("apple", 9);
        Product orange = new Product("orange", 5);
        Product mango = new Product("mango", 6);
        Product pear = new Product("pear", 4);
        System.out.println(pear.getId()); // 5

        fruitStock.addProduct(expensiveApple);
        fruitStock.addProduct(cheapApple);
        System.out.println(fruitStock.getProducts()); // expensiveApple, cheapApple

        Optional<Product> apple = fruitStock.getProduct("apple"); // Optional.of(cheapApple)
        apple.ifPresent(System.out::println); // cheapApple

        fruitStock.addProduct(orange);
        fruitStock.addProduct(mango);
        System.out.println(fruitStock.getProducts().size()); // 4
        System.out.println(fruitStock.getProducts("apple")); // cheapApple, expensiveApple

        try {
            fruitStock.addProduct(pear);
        } catch (StockException e) {
            System.out.println(e.getReason()); // STOCK_IS_FULL
        }

        try {
            fruitStock.addProduct(mango);
        } catch (StockException e) {
            System.out.println(e.getReason()); // STOCK_ALREADY_CONTAINS_PRODUCT
        }

        Optional<Product> removedMango = fruitStock.removeProduct("mango"); // Optional.of(mango)
        removedMango.ifPresent(System.out::println);

        System.out.println(fruitStock.removeProduct("apple")); // Optional[cheapApple]
        System.out.println(fruitStock.removeProduct("apple").get()); // Optional[]
        System.out.println(fruitStock.removeProduct("dumpling")); // Optional.empty

    }
}

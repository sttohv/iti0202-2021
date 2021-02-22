package ee.taltech.iti0202.stock.stock;

import ee.taltech.iti0202.stock.exceptions.StockException;
import ee.taltech.iti0202.stock.product.Product;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The stock class.
 * <p>
 * Each stock has a name, list of products that are currently stored in it
 * and the maximum amount of products that stock can store.
 * <p>
 * If adding a product to the stock is not possible, due to exceeding the maximum limit of products
 * OR stock already contains a product, a StockException must be thrown,
 * otherwise product must be added to the stock.
 * <p>
 * When getting (not removing) a product from our stock,
 * it is important to find the product with the lowest price first.
 */

public class Stock {
    private String stockName;
    private int stockMaxCapacity;
    private List<Product> stockProducts;

    /**
     * Create a new stock with the given name and the max capacity for the products.
     *
     * @param name        the name of the stock.
     * @param maxCapacity max amount of products allowed in the stock.
     */
    public Stock(String name, int maxCapacity) {
        stockName = name;
        stockMaxCapacity = maxCapacity;
        stockProducts = new ArrayList<>();

    }

    /**
     * Add a product to the stock, if stock does not contain the product and is not full yet.
     * <p>
     * Check in following order:
     * If stock already contains a product, throw an StockException with a STOCK_ALREADY_CONTAINS_PRODUCT reason.
     * If stock is full, throw a StockException with a STOCK_IS_FULL reason.
     *
     * @param product to be added
     * @throws StockException STOCK_ALREADY_CONTAINS_PRODUCT, STOCK_IS_FULL
     */

    public void addProduct(Product product) throws StockException {
        if (stockProducts.contains(product)) {

            throw new StockException(StockException.Reason.STOCK_ALREADY_CONTAINS_PRODUCT);

        } else if (isFull()) {

            throw new StockException(StockException.Reason.STOCK_IS_FULL);

        } else {
            if (product.getPrice() > 0) {
                stockProducts.add(product);
            } else {

                throw new StockException(StockException.Reason.NEGATIVE_PRICE);

            }
        }
    }

    /**
     * Get a product from a stock by name with the lowest price.
     * <p>
     * If there are several products with the same name and price,
     * returns the product with the lowest id.
     *
     * @param name the product's name
     * @return Optional
     */
    public Optional<Product> getProduct(String name) {
        List<Product> sameNameProducts = stockProducts
                .stream()
                .filter(s -> s.getName().equals(name))
                .collect(Collectors.toList());
        if (sameNameProducts.size() == 0) {
            return Optional.empty();
        } else if (sameNameProducts.size() == 1) {
            return Optional.of(sameNameProducts.get(0));
        } else {
            Product lowestPriceProduct = Collections.min(sameNameProducts, Comparator.comparing(Product::getId));
            return Optional.of(lowestPriceProduct);
        }


    }

    /**
     * Remove and return a product from a stock,
     * if stock has a given product.
     * <p>
     * Use getProduct() method to get the product.
     * <p>
     * If there is nothing to remove, return Optional.empty()
     *
     * @param name Name of the product to be removed
     * @return Optional
     */

    public Optional<Product> removeProduct(String name) {
        if (getProduct(name).isPresent()) {
            Product namedProduct = getProduct(name).get();
            stockProducts.remove(namedProduct);
            return Optional.of(namedProduct);
        }
        return Optional.empty();
    }

    /**
     * Get a list of current products in the stock.
     *
     * @return List
     */
    public List<Product> getProducts() {
        return stockProducts;
    }

    /**
     * Get a list of current products in the stock filtered by name.
     * <p>
     * Order the products by price ascending. In case of the same price, by id ascending.
     *
     * @param name Name to be filtered.
     * @return List
     */
    public List<Product> getProducts(String name) {
        return stockProducts
                .stream()
                .filter(s -> s.getName().equals(name))
                .sorted(Comparator.comparing(Product::getId))
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
    }

    /**
     * Get total price of all the products.
     *
     * @return Total price.
     */
    public int getTotalPrice() {
        int sum = 0;
        for (Product product : stockProducts
        ) {
            sum += product.getPrice();
        }

        return sum;
    }

    /**
     * Check if stock is full.
     *
     * @return boolean
     */
    public boolean isFull() {
        return stockProducts.size() >= stockMaxCapacity;
    }

}

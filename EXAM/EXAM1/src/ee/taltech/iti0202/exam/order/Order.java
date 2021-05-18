package ee.taltech.iti0202.exam.order;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int index = 0;
    private int orderId;
    private List<Product> orderedProducts;
    private boolean cancelled;

    /**
     * Order constructor
     */
    public Order(int id) {
        this.orderedProducts = new ArrayList<>();
        orderId = id;
        cancelled = false;
    }

    /**
     * Get and increment product id
     *
     * @return product id
     */
    public static int getAndIncrementNextId() {
        index++;
        return index;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    /**
     * Add product to order if it isn't already there
     *
     * @param product Product to add to order
     * @return if product could be added or not
     */
    public boolean canAddProductToOrder(Product product) {
        return !isCancelled() && !orderedProducts.contains(product);
    }

    /**
     * Add product to order
     *
     * @param product product to be added
     */
    public void addProductToOrder(Product product) {
        orderedProducts.add(product);
    }


    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Cancel order
     */
    public void cancelOrder() {
        cancelled = true;
        orderedProducts = new ArrayList<>();
    }
}

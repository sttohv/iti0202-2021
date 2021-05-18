package ee.taltech.iti0202.exam.order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Shop {
    private List<Product> availableProducts = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private int index = 0;

    /**
     * Add product if not already in available products
     *
     * @param product product to add
     * @return if could be added
     */
    public boolean addProduct(Product product) {
        if (!availableProducts.contains(product)) {
            availableProducts.add(product);
            return true;
        }
        return false;
    }

    /**
     * Creates a new order
     *
     * @return order id
     */
    public int createNewOrder() {
        index++;
        Order order = new Order(index);
        orders.add(order);
        return order.getOrderId();
    }

    /**
     * Add product to order
     *
     * @param orderNumber order number to get order
     * @param itemName    product name to get product
     * @return if could be added or not
     */
    public boolean addProductToOrder(int orderNumber, String itemName) {
        Optional<Order> order = orders.stream().filter(i -> i.getOrderId() == orderNumber).findFirst();
        Optional<Product> product = availableProducts
                .stream()
                .sorted(Comparator.comparingInt(Product::getPrice))
                .filter(i -> i.getName().equals(itemName))
                .findFirst();

        if (order.isEmpty() || product.isEmpty()) {
            return false;
        } else {
            if (order.get().canAddProductToOrder(product.get())) {
                order.get().addProductToOrder(product.get());
                availableProducts.remove(product.get());
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Get sum of all the products in order
     *
     * @param orderNumber number to get order by
     * @return the sum of products
     */
    public int getOrderSum(int orderNumber) {
        Optional<Order> order = orders.stream().filter(i -> i.getOrderId() == orderNumber).findFirst();
        int sum = 0;
        if (order.isPresent()) {
            if (order.get().getOrderedProducts().size() == 0 || order.get().isCancelled()) {
                return 0;
            } else {
                for (Product product : order.get().getOrderedProducts(
                )) {
                    sum += product.getPrice();
                }
                return sum;
            }
        } else {
            return -1;
        }
    }

    /**
     * Cancel order if not already cancelled and the order has to exist
     *
     * @param orderNumber order number to get order by
     * @return if order could be cancelled
     */
    public boolean cancelOrder(int orderNumber) {
        Optional<Order> order = orders.stream().filter(i -> i.getOrderId() == orderNumber).findFirst();
        if (order.isEmpty() || order.get().isCancelled()) {
            return false;
        } else {
            List<Product> products = order.get().getOrderedProducts();
            order.get().cancelOrder();
            for (Product product : products
            ) {
                availableProducts.add(product);
            }
            return true;
        }

    }

    public List<Product> getAvailableProducts() {
        return availableProducts;
    }
}

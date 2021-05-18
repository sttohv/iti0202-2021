package ee.taltech.iti0202.exam.order;

public class Main {

    public static final int RADIO_PRICE = 60;
    public static final int TV_PRICE = 90;
    public static final int PIIM_PRICE = 90;
    public static final int TV_PRICE2 = 100;

    public static void main(String[] args) {

        Shop topShop = new Shop();
        Product p1 = new Product("TV", TV_PRICE2);
        Product p2 = new Product("Radio", RADIO_PRICE);
        Product p3 = new Product("TV", TV_PRICE);
        Product p5 = new Product("piim", PIIM_PRICE);
        topShop.addProduct(p1);
        topShop.addProduct(p2);
        topShop.addProduct(p3);
        topShop.addProduct(p5);
        int orderNumber1 = topShop.createNewOrder();
        System.out.println(orderNumber1); // 1
        System.out.println(topShop.addProductToOrder(orderNumber1, "Radio")); // true
        System.out.println(topShop.addProductToOrder(orderNumber1, "Cheese")); // false
        System.out.println(topShop.addProductToOrder(orderNumber1, "Radio")); // false

        Product p4 = new Product("TV", 100);
        System.out.println(topShop.addProduct(p3)); // false - same product instance not allowed
        System.out.println(topShop.addProduct(p4)); // true - another product with same values is allowed

        int orderNumber2 = topShop.createNewOrder();
        System.out.println(orderNumber2);
        System.out.println(topShop.addProductToOrder(orderNumber2, "TV")); // true
        System.out.println(topShop.getOrderSum(orderNumber2)); // 90
        System.out.println(topShop.addProductToOrder(orderNumber2, "TV")); // true
        System.out.println(topShop.getOrderSum(orderNumber2)); // 190
        System.out.println(topShop.addProductToOrder(orderNumber2, "TV")); // true
        System.out.println(topShop.getOrderSum(orderNumber2)); // 290
        System.out.println(topShop.addProductToOrder(orderNumber2, "TV")); // false
        System.out.println(topShop.getOrderSum(orderNumber2)); // 290

// cancel order
        topShop.cancelOrder(orderNumber1); // free all the products from order 1
//        int orderNumber3 = topShop.createNewOrder();
//        System.out.println(orderNumber3); // 3
//        System.out.println(topShop.addProductToOrder(orderNumber3, "Radio")); // true
//        System.out.println(topShop.getOrderSum(orderNumber3)); // 60
    }
}

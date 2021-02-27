package ee.taltech.iti0202.tk2.shop;

/**
 * sdcjsdjc
 */
public class Product {

    private String productName;
    private int productPrice;

    /**
     * jf
     *
     * @param name  dfijvi
     * @param price dfj jf
     */
    public Product(String name, int price) {
        productName = name;
        productPrice = price;
    }

    /**
     * kjsdncj
     *
     * @param price idjfv
     */
    public Product(int price) {
        productPrice = price;
    }

    /**
     * sfnc
     *
     * @return dfij
     */
    public int getPrice() {
        return productPrice;
    }

    /**
     * jjfcn
     *
     * @return jfv
     */
    public String getName() {
        return productName;
    }

    /**
     * sdjcnjsnc
     *
     * @return ijsf
     */
    @Override
    public String toString() {
        if (this == null && productName.equals(null)) {
            return "(" + productPrice + ")";
        } else {
            return productName + " (" + productPrice + ")";
        }
    }
}

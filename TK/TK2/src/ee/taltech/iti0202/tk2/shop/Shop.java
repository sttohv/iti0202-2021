package ee.taltech.iti0202.tk2.shop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * sdcjn
 */
public class Shop {
    private List<Product> allProducts;

    /**
     * sdcj
     */
    public Shop() {
        allProducts = new ArrayList<>();
    }

    /**
     * jc
     *
     * @param product c
     * @return dj
     */
    public boolean addProduct(Product product) {
        if (product.getPrice() > 0 && !allProducts.contains(product)) {
            allProducts.add(product);
            return true;
        }
        return false;
    }

    /**
     * scnjn
     *
     * @param name     djf
     * @param maxPrice kjdf
     * @return jdf
     */
    public Optional<Product> sellProduct(String name, int maxPrice) {
        List<Product> productsUnderMaxPrice = allProducts
                .stream()
                .filter(n -> n.getPrice() < maxPrice && n.getName().equals(name))
                .sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
        if (productsUnderMaxPrice.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(productsUnderMaxPrice.get(0));
        }
    }

    /**
     * sdcnj
     *
     * @return ijdf
     */
    public List<Product> getProducts() {
        return allProducts;
    }
}

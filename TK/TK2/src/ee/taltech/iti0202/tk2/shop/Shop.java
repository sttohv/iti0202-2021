package ee.taltech.iti0202.tk2.shop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Shop {
    private List<Product> allProducts;

    public Shop() {
        allProducts = new ArrayList<>();
    }

    public boolean addProduct(Product product) {
        if (product.getProductPrice() > 0 && !allProducts.contains(product)) {
            allProducts.add(product);
            return true;
        }
        return false;
    }

    public Optional<Product> sellProduct(String name, int maxPrice) {
        List<Product> productsUnderMaxPrice = allProducts
                .stream()
                .filter(n -> n.getProductPrice() < maxPrice && n.getProductName().equals(name))
                .sorted(Comparator.comparing(Product::getProductPrice)).collect(Collectors.toList());
        if (productsUnderMaxPrice.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(productsUnderMaxPrice.get(0));
        }
    }

    public List<Product> getProducts() {
        return allProducts;
    }
}

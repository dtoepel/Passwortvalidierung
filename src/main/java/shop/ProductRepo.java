package shop;

import java.util.HashMap;
import java.util.Vector;

public class ProductRepo {
    private final HashMap<Product, Integer> products = new HashMap<>();

    public void removeProduct(Product product) {throw new RuntimeException("Not yet implemented: removeProduct");}
    public Vector<Product> getAllProducts() {throw new RuntimeException("Not yet implemented: getAllProducts");}
    public Vector<Product> getAllProducts(ProductFilter f) {throw new RuntimeException("Not yet implemented: getAllProducts");}

    public void addProduct(Product product, int quantity) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public Product getProduct(String productID) {
        for(Product p : products.keySet()) {
            if(p.productID().equals(productID)) {return p;}
        }
        return null;
    };

    public Integer getStock(Product product) {
        return products.getOrDefault(product, 0);
    }

    public boolean canFulfill(Order order) {
        HashMap<Product, Integer> totalOrder = order.getTotal();
        for(Product p : totalOrder.keySet()) {
            if(totalOrder.get(p).compareTo(totalOrder.get(p)) > 0) {
                return false;}}
        return true;
    }

    public void removeStock(Order order) {
        HashMap<Product, Integer> totalOrder = order.getTotal();
        for(Product pInOrder : totalOrder.keySet()) {
            addProduct(pInOrder, -totalOrder.get(pInOrder));
        }
    }

    public static interface ProductFilter {
        public boolean accept(Product product);
    }
}

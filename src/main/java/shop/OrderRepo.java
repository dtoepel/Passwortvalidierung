package shop;

import java.util.Vector;

public class OrderRepo {
    private final Vector<Order> orders = new Vector<>();

    public void addOrder(Order order) {
        orders.add(order);
    }
    public void removeOrder(Order order) {throw new RuntimeException("Not yet implemented: removeOrder");}
    public Vector<Order> getOrder(String orderID) {throw new RuntimeException("Not yet implemented: getOrder");}
    public Vector<Order> getAllOrders() {throw new RuntimeException("Not yet implemented: getAllOrders");}
    public Vector<Order> getAllOrders(OrderFilter f) {throw new RuntimeException("Not yet implemented: getAllOrders");}

    public static interface OrderFilter {
        public boolean accept(Order order);
    }

}

package shop;

import java.util.HashMap;
import java.util.Vector;

public class Order {
    private final Customer customer;
    private final Vector<OrderItem> orderItems = new Vector<>();

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addOrderItem(OrderItem p) {
        orderItems.add(p);
    }

    public void removeOrderItem(Product p) {
        Vector<OrderItem> temp = new Vector<>();
        for(OrderItem item : orderItems) {
            if(item.product() == p) {
                temp.add(item);
            }
        }
        orderItems.removeAll(temp);
    }

    public void removeOrderItem(OrderItem p) {throw new RuntimeException("Not yet implemented: removeOrderItem");}
    public Vector<OrderItem> getAllOrderItems() {throw new RuntimeException("Not yet implemented: getAllOrderItems");}

    public void print() {
        System.out.println("Order no. x\n");
        System.out.println(customer.firstName() + " " + customer.lastName());
        System.out.println(customer.address().street());
        System.out.println(customer.address().zip() + " " + customer.address().city());
        System.out.println(customer.address().country());
        System.out.println();
        Money sum = new Money(0.00, "EUR");
        for(OrderItem item : orderItems) {
            String qty = item.quantity()+"";
            String name = item.product().name();
            Money priceM = item.reducedPrice()==null?item.product().price():item.reducedPrice();
            String price = priceM.toString();

            for(int i = 0; i < item.quantity(); i++) {
                sum = sum.add(priceM);
            }

            while(qty.length()<5) qty = " "+qty;
            while(name.length()<40) name = name+" ";
            while(price.length()<12) price = " "+price;

            System.out.println(qty+" | "+name+" | "+price);
        }

        String sumS = sum.toString();
        while(sumS.length()<12) sumS = " "+sumS;
        System.out.println("------|------------------------------------ Sum: | " +sumS);
    }

    public HashMap<Product, Integer> getTotal() {
        HashMap<Product, Integer> result = new HashMap<>();
        for(OrderItem item : orderItems) {
            Product product = item.product();
            if(result.containsKey(product)) {
                result.put(product, result.get(product)+item.quantity());
            } else {
                result.put(product, item.quantity());
            }
        }
        return result;
    }
}

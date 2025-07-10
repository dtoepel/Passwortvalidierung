package shop;

import records.Address;

import java.util.Scanner;
import java.util.Vector;

public class ShopService {
    private transient Customer customer;
    private transient Order order;
    private final Vector<Customer> customers = new Vector<>();
    private final ProductRepo productRepo = new ProductRepo();
    private final OrderRepo orderRepo = new OrderRepo();


    public static void main(String[] args) {
        ShopService shop = new ShopService();
        shop.initShop();
        shop.startUI();
    }

    private void initShop() {
        Address addressD = new Address("123 Fake Street", "01234", "Daltonville", "Somewhere in the South West");
        customers.add(new Customer("Joe", "Dalton", "DALJOE0", addressD));
        customers.add(new Customer("William", "Dalton", "DALWIL0", addressD));
        customers.add(new Customer("Jack", "Dalton", "DALJAC0", addressD));
        customers.add(new Customer("Averell", "Dalton", "DALAVE0", addressD));

        productRepo.addProduct(new Product("colt7", "Siebenschüssiger Colt", new Money(179.77, "USD")), 3);
    }

    private void startUI() {
        Scanner scanner = new Scanner(System.in);
        String input;
        OrderState state = OrderState.START;
        do {
            state.promptUser(this);
            input = scanner.nextLine();
            if(!("exit".equalsIgnoreCase(input))) state = state.process(input, this);
        } while (!("exit".equalsIgnoreCase(input)));

    }

    public void println(String s) {
        System.out.println(s);
    }

    public void printlnError(String s) {
        System.err.println(s);
    }

    public void setCurrentCustomer(String customerID) {
        this.customer = getCustomer(customerID);
        if(customer == null) throw new RuntimeException("Customer " + customerID + " not found.");
    }

    private Customer getCustomer(String customerID) {
        for(Customer customer : customers) {
            if(customer.customerID().equals(customerID)) {
                return customer;
            }
        }
        return null;
    }

    public void createOrder() {
        order = new Order(customer);
    }

    public void addToOrder(String productID, int amount) {
        if(order == null) {throw new RuntimeException("Order is null");}
        Product product = productRepo.getProduct(productID);
        if(product == null) {throw new RuntimeException("productID not found");}
        if(productRepo.getStock(product) < amount) {throw new RuntimeException("not enough in stock");}
        order.addOrderItem(new OrderItem(product, amount, null));
    }

    public void removeFromOrder(String productID) {
        if(order == null) {throw new RuntimeException("Order is null");}
        Product product = productRepo.getProduct(productID);
        if(product == null) {throw new RuntimeException("productID not found");}
        order.removeOrderItem(product);
    }

    public void printCurrentOrder() {
        if(order != null) order.print();
    }

    public void checkoutCurrentOrder() {
        // check availability
        if(productRepo.canFulfill(order)) {
            // remove stock
            productRepo.removeStock(order);
            // add to archive
            orderRepo.addOrder(order);
        } else {
            throw new RuntimeException("Cannot fulfill order. To few items in stock.");
        }
    }
}

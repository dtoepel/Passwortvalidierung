package shop;

public abstract class OrderState {
    public abstract OrderState process(String input, ShopService shop);
    public abstract void promptUser(ShopService shop);

    public static OrderState START = new OrderState() {
        @Override
        public OrderState process(String input, ShopService shop) {
            try{
                shop.setCurrentCustomer(input);
                shop.createOrder();
                shop.println("Customer accepted, order open!");
                return OrderState.ORDER_OPEN;
            } catch (Exception e) {
                shop.printlnError(e.getMessage());
                return this;
            }
        }

        @Override
        public void promptUser(ShopService shop) {
            shop.println("No customer selected yet.");
            shop.println("Enter customer id:");
        }
    };

    public static OrderState ORDER_OPEN = new OrderState() {
        @Override
        public OrderState process(String input, ShopService shop) {
            if(input.toLowerCase().startsWith("add ")) {
                // adding
                String[] parts = input.split(" ");
                if(parts.length != 3) {
                    shop.printlnError("command invalid, needs 2 arguments");
                    return this;}
                int amount = 0;
                try{
                    amount = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    shop.printlnError("command invalid, 2nd argument must be a number");
                    return this;
                }
                try{
                    shop.addToOrder(parts[2], amount);
                    return this;
                } catch (Exception e) {
                    shop.printlnError("cannot add item: " + e.getMessage());
                    return this;
                }
            } else if(input.toLowerCase().startsWith("remove ")) {
                // removing

                String[] parts = input.split(" ");
                if(parts.length != 2) {
                    shop.printlnError("command invalid, needs 1 argument");
                    return this;}
                try{
                    shop.removeFromOrder(parts[1]);
                    return this;
                } catch (Exception e) {
                    shop.printlnError("cannot remove item: " + e.getMessage());
                    return this;
                }
            } else if(input.equalsIgnoreCase("print")) {
                // just printing
                shop.printCurrentOrder();
                return this;
            } else if(input.equalsIgnoreCase("finish")) {
                // check availability
                // reduce stock
                // return to start
                try{
                    shop.checkoutCurrentOrder();
                    return START;
                } catch (Exception e) {
                    shop.printlnError("cannot check out: " + e.getMessage());
                    return this;
                }
            } else {
                shop.printlnError("command invalid");
                return this;
            }
        }

        @Override
        public void promptUser(ShopService shop) {
            shop.println("Type one of the following commands:");
            shop.println("ADD amount productID");
            shop.println("REMOVE productID");
            shop.println("PRINT");
            shop.println("FINISH");
        }
    };
}

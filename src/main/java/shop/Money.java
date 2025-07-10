package shop;

import java.text.DecimalFormat;

public record Money(int amount, String currency) {
    private static DecimalFormat df = new DecimalFormat("###,###.00");

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money(double amount, String currency) {
        this((int) (.5+amount*100),currency);
    }

    @Override
    public String toString() {
        return df.format(amount/100.) + " " +  currency ;
    }

    public Money add(Money priceM) {
        int amount = priceM.amountIn(this.currency);
        return new Money(amount + this.amount, this.currency);
    }

    private int amountIn(String currency) {
        int amount = this.amount;
        if(this.currency.equals(currency)) { return amount; }
        if(this.currency.equals("USD")) { amount = (int) (.5 + amount/1.1); }
        if(currency.equals("USD")) { amount = (int) (.5 + amount*1.1); }
        return amount;
    }
}

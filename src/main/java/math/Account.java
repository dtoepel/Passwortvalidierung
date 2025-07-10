package math;

import util.MyList;

import java.math.BigDecimal;
import java.util.Vector;

public class Account {
    private final String accountNo;
    private BigDecimal balance;
    private final Vector<Client> clients;

    public Account(Vector<Client> clients , String accountNo) {
        this.accountNo = accountNo;
        this.clients = new Vector<>();
        this.clients.addAll(clients);
        this.balance = BigDecimal.ZERO;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public Vector<Client> getClients() {
        return clients;
    }

    public void deposit(BigDecimal amount) {
        //if(amount.compareTo(BigDecimal.ZERO) < 0) {throw new IllegalArgumentException();}
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        this.deposit(amount.negate());
       //if(amount.compareTo(BigDecimal.ZERO) < 0) {throw new IllegalArgumentException();}
        //this.balance = this.balance.subtract(amount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNo='" + accountNo + '\'' +
                ", balance=" + balance +
                ", clients=" + clients +
                '}';
    }
}

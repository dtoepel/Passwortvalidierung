package math;

import records.Animal;
import util.MyList;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Vector;

public class BankService {
    private final Vector<Account> accounts = new Vector<>();

    public static void main(String[] args) {
        BankService bank = new BankService();
        Client c1 = new Client("John", "Smith", "1234");
        Client c2 = new Client("William", "Smith", "2345");
        Vector<Client> C1 = new Vector<>();
        Vector<Client> C2 = new Vector<>();
        Vector<Client> C12 = new Vector<>();
        C1.add(c1); C12.add(c1);
        C2.add(c2); C12.add(c2);
        String no1 = bank.openAccount(C1);
        String no2 = bank.openAccount(C2);
        String no3 = bank.openAccount(C12);
        bank.transfer(no1, no2, new BigDecimal("123.40"));
        bank.findAccount(no3).deposit(new BigDecimal("0.03"));
        bank.split(no3);
        bank.addInterest(7.7);
        System.out.println(bank.accounts);
    }

    public String openAccount(Vector<Client> clients) {
        int i = 0;
        while(accountExists(i)) {
            i++;
        }
        String number = getNumber(i);

        Account a = new Account(clients, number);
        accounts.add(a);
        return number;
    }

    public void transfer(String fromNo, String toNo, BigDecimal amount) {
        Account aFrom = findAccount(fromNo);
        Account aTo = findAccount(toNo);
        if(aFrom != null && aTo != null) {
            aFrom.withdraw(amount);
            aTo.deposit(amount);
        } else {
            throw new RuntimeException("Account doesn't exist");
        }
    }

    public Vector<String> split(String oldAccountNumber) {
        Account oldAccount = findAccount(oldAccountNumber);
        if(oldAccount == null) {throw new IllegalArgumentException("Account does not exist");}
        if(oldAccount.getClients().size() < 2) {throw new IllegalArgumentException("Account cannot be split.");}
        if(oldAccount.getBalance().compareTo(BigDecimal.ZERO) <= 0) {throw new IllegalArgumentException("Account balance cannot be split.");}
        Vector<String> newAccountNumbers = new Vector<>();
        BigDecimal amountPerCustomer = oldAccount.getBalance()
                .divide(new BigDecimal(oldAccount.getClients().size()), 2, RoundingMode.FLOOR);
        for(Client c : oldAccount.getClients()) {
            Vector<Client> clients = new Vector<>();
            clients.add(c);
            String newAccountNumber = openAccount(clients);
            newAccountNumbers.add(newAccountNumber);
            transfer(oldAccountNumber, newAccountNumber, amountPerCustomer);
        }

        for(String newAccountNumber : newAccountNumbers) {
            if(oldAccount.getBalance().compareTo(BigDecimal.ZERO) > 0) {
                transfer(oldAccountNumber, newAccountNumber, new BigDecimal("0.01"));
            }
        }
        accounts.remove(oldAccount);
        return newAccountNumbers;
    }

    public void addInterest(double ratePercent) {
        for (Account a : accounts) {
            a.deposit(new BigDecimal(ratePercent)
                    .multiply(a.getBalance())
                    .divide(new BigDecimal("100"),2, RoundingMode.HALF_UP));
        }
    }

    private Account findAccount(String number) {
        for (Account a : accounts) {if(a.getAccountNo().equals(number)) {return a;}}
        return null;
    }

    private boolean accountExists(int i) {
        String number = getNumber(i);
        for (Account a : accounts) {if(a.getAccountNo().equals(number)) {return true;}}
        return false;
    }

    private static String getNumber(int i) {
        String s = "" + i;
        while(s.length() < 6) s = "0" + s;
        return s;
    }


}

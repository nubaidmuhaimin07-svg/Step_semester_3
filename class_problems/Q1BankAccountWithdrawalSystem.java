import java.util.HashMap;
import java.util.Map;

public class Q1BankAccountWithdrawalSystem {
    static class Account {
        String id;
        int balance;
        Account(String id, int balance) {
            this.id = id;
            this.balance = balance;
        }
        String withdraw(int amount) {
            return "";
        }
    }

    static class SavingsAccount extends Account {
        SavingsAccount(String id, int balance) {
            super(id, balance);
        }
        String withdraw(int amount) {
            if (balance - amount < 1000)
                return id + " rejected: minimum balance 1000";
            balance -= amount;
            return id + " balance " + balance;
        }
    }

    static class CurrentAccount extends Account {
        CurrentAccount(String id, int balance) {
            super(id, balance);
        }
        String withdraw(int amount) {
            if (balance - amount < -5000)
                return id + " rejected: overdraft limit 5000";
            balance -= amount;
            return id + " balance " + balance;
        }
    }

    public static void main(String[] args) {
        Map<String, Account> accounts = new HashMap<>();
        accounts.put("S1", new SavingsAccount("S1", 5000));
        System.out.println(accounts.get("S1").withdraw(4500));

        accounts.put("C1", new CurrentAccount("C1", 2000));
        System.out.println(accounts.get("C1").withdraw(6000));
        System.out.println(accounts.get("S1").withdraw(3000));
        System.out.println(accounts.get("C1").withdraw(2000));

        Account account = accounts.get("X1");
        if (account == null) System.out.println("Account not found");
    }
}

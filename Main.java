import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    private String generateAccountNumber() {
        return "AC" + (int)(Math.random() * 10000);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: " + amount);
    }

    public void withdraw(double amount) throws Exception {
        if (amount < 100) {
            throw new Exception("Minimum withdrawal amount is 100.");
        }
        if (amount > balance) {
            throw new Exception("Insufficient balance.");
        }
        balance -= amount;
        transactionHistory.add("Withdrew: " + amount);
    }
}

class ATM {
    private HashMap<String, Account> accounts;

    public ATM() {
        accounts = new HashMap<>();
    }

    public void createAccount(String name) {
        Account account = new Account(name);
        accounts.put(account.getAccountNumber(), account);
        System.out.println("Account created: " + account.getAccountNumber());
    }

    public void deposit(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposited: " + amount + " to account: " + accountNumber);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            try {
                account.withdraw(amount);
                System.out.println("Withdrew: " + amount + " from account: " + accountNumber);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void checkBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Balance for account " + accountNumber + ": " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void viewTransactionHistory(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Transaction history for account " + accountNumber + ": " + account.getTransactionHistory());
        } else {
            System.out.println("Account not found.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Create Account\n2. Deposit\n3. Withdraw\n4. Check Balance\n5. View Transaction History\n6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account holder name: ");
                    String name = scanner.nextLine();
                    atm.createAccount(name);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    String depositAccount = scanner.nextLine();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAccount, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    String withdrawAccount = scanner.nextLine();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAccount, withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    String balanceAccount = scanner.nextLine();
                    atm.checkBalance(balanceAccount);
                    break;
                case 5:
                    System.out.print("Enter account number: ");
                    String historyAccount = scanner.nextLine();
                    atm.viewTransactionHistory(historyAccount);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

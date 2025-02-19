import java.util.*;


// Interface for common account operations
interface AccountOperations {
    void deposit(double amount);
    void withdraw(double amount);
    void displayInfo();
}

// Abstract class for common account features
abstract class BankAccount implements AccountOperations {
    protected String accountNumber;
    protected String accountHolder;
    protected String address;
    protected double balance;
    protected String password;

     public BankAccount(String accountNumber, String accountHolder, String address, double balance, String password) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.address = address;
        this.balance = balance;
        this.password = password;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);  //Deposit method implemented here.
            displayBalance();
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 &&  balance- amount>1000 ) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);        //Withdraw method implemented here.
            displayBalance();
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
    // Method to set the account holder's name
    public void setAccountHolder(String newAccountHolder) {
        this.accountHolder = newAccountHolder;
        System.out.println("Account Holder Name updated successfully!");
        displayInfo();
    }
    @Override
    public void displayInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);  //DisplayInfo method implemented here.
        System.out.println("Address: " + address);   
        displayBalance();
    }

    private void displayBalance() {
        System.out.println("Current Balance: $" + balance);
    }

    // Method to verify the password
    public boolean verifyPassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }
}

// Final class representing a Savings Account
final class SavingsAccount extends BankAccount {
    public SavingsAccount(String accountNumber, String accountHolder, String address, double balance, String password) {
        super(accountNumber, accountHolder, address, balance, password);
    }
}

// Bank Management System class
public class BankManagementSystem {
    private Map<String, SavingsAccount> accounts;

    public BankManagementSystem() {
        this.accounts = new HashMap<>();
    }

// Method to create a new account
public void createAccount() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter Account Number: ");   //user defined account number
    String accountNumber = scanner.nextLine();

    // Check if an account with the same account number already exists
    if (accounts.containsKey(accountNumber)) {
        System.out.println("Account with the same account number already exists. Please choose a different account number.");
        return;  // Exit the method without creating a new account
    }

    System.out.println("Enter Account Holder Name: ");  //account holder name
    String accountHolder = scanner.nextLine();

    System.out.println("Enter Address: ");  // address of account holder
    String address = scanner.nextLine();

    System.out.println("Enter Initial Balance: "); //user defined initial balance
    double initialBalance = scanner.nextDouble();

    scanner.nextLine(); // Consume the newline character for string input

    System.out.println("Set a Password: ");       // Password input
    String password = scanner.nextLine();

    SavingsAccount newAccount = new SavingsAccount(accountNumber, accountHolder, address, initialBalance, password);
    
    // Add the new account only if it doesn't already exist
    accounts.put(accountNumber, newAccount);

    System.out.println("\t\t\t\u001B[32m" + "CONGRATULATIONS !" + "\u001B[37m" + "\n  \t\tAccount created successfully!");
}

    // Method to search for an account
    public void searchAccount(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            SavingsAccount account = accounts.get(accountNumber); //account number is the key to search for account

            System.out.println("Enter Account Password: ");
            Scanner scanner = new Scanner(System.in);          //password validation
            String enteredPassword = scanner.nextLine();

            if (account.verifyPassword(enteredPassword)) {
                System.out.println("Account found:");
                account.displayInfo();
            } else {
                System.out.println("INVALID PASSWORD !!!. Access denied.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to deposit money into an account
    public void depositMoney(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {                   //account number is the key to search for account
            SavingsAccount account = accounts.get(accountNumber);

            System.out.println("Enter Account Password: ");
            Scanner scanner = new Scanner(System.in);               //password validation
            String enteredPassword = scanner.nextLine();

            if (account.verifyPassword(enteredPassword)) {
                account.deposit(amount);
            } else {
                System.out.println("INVALID PASSWORD !!!. Access denied.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to withdraw money from an account
    public void withdrawMoney(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            SavingsAccount account = accounts.get(accountNumber);

            System.out.println("Enter Account Password: ");
            Scanner scanner = new Scanner(System.in);
            String enteredPassword = scanner.nextLine();

            if (account.verifyPassword(enteredPassword)) {
                account.withdraw(amount);
            } else {
                System.out.println("INVALID PASSWORD !!!. Access denied.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }
    // Method to check the balance of an account
    public void checkBalance(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            SavingsAccount account = accounts.get(accountNumber);

            System.out.println("Enter Account Password: ");
            Scanner scanner = new Scanner(System.in);
            String enteredPassword = scanner.nextLine();

            if (account.verifyPassword(enteredPassword)) {
                System.out.println("Account Balance:");
                account.displayInfo();
            } else {
                System.out.println("INVALID PASSWORD !!!. Access denied.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to edit the account holder's name
    public void editAccountHolderName(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            SavingsAccount account = accounts.get(accountNumber);

            System.out.println("Enter Account Password: ");
            Scanner scanner = new Scanner(System.in);
            String enteredPassword = scanner.nextLine();

            if (account.verifyPassword(enteredPassword)) {
                System.out.println("Enter New Account Holder Name: ");
                String newAccountHolderName = scanner.nextLine();
                account.setAccountHolder(newAccountHolderName);
            } else {
                System.out.println("INVALID PASSWORD !!!. Access denied.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }
    public static void main(String[] args) {
        BankManagementSystem bankSystem = new BankManagementSystem();
        Scanner scanner = new Scanner(System.in);
        //System.out.println("\n\t\"+"\u001B[36m"+"WELCOME"+"\u001B[37m");
        System.out.println("\n\t\t\t" + "\u001B[36m" + "WELCOME TO HOWITZER BANK" + "\u001B[37m");
        System.out.println("\t\u001B[36m" + "      Your Trusted Partner in Financial Services" + "\u001B[37m");
        while (true) {
            System.out.println("\u001B[32m"+"\nBank Management System Menu:"+"\u001B[37m");
            System.out.println("1. Create New Account");
            System.out.println("2. Search for Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Check Balance");
            System.out.println("6. Edit Account Holder Name");
            System.out.println("7. Exit");
            System.out.println("\u001B[34m"+"Enter your choice: "+"\u001B[37m");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bankSystem.createAccount();
                    break;
                case 2:
                    System.out.println("Enter Account Number to Search: ");
                    scanner.nextLine(); // Consume the newline character
                    String searchAccountNumber = scanner.nextLine();
                    bankSystem.searchAccount(searchAccountNumber);
                    break;
                case 3:
                    System.out.println("Enter Account Number to Deposit Money: ");
                    scanner.nextLine(); // Consume the newline character
                    String depositAccountNumber = scanner.nextLine();
                    System.out.println("Enter Deposit Amount: ");
                    double depositAmount = scanner.nextDouble();
                    bankSystem.depositMoney(depositAccountNumber, depositAmount); //banksystem object has an method
                    break;                                                        //called deposit money which is used
                case 4:
                    System.out.println("Enter Account Number to Withdraw Money: ");
                    scanner.nextLine(); // Consume the newline character
                    String withdrawAccountNumber = scanner.nextLine();
                    System.out.println("Enter Withdrawal Amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    bankSystem.withdrawMoney(withdrawAccountNumber, withdrawAmount);
                    break;
                case 5:
                    System.out.println("Enter Account Number to Check Balance: ");
                    scanner.nextLine(); // Consume the newline character
                    String checkBalanceAccountNumber = scanner.nextLine();
                    bankSystem.checkBalance(checkBalanceAccountNumber);
                    break;
                case 6:
                    System.out.println("Enter Account Number to Edit Account Holder Name: ");
                    scanner.nextLine(); // Consume the newline character
                    String editAccountHolderNameNumber = scanner.nextLine();
                    bankSystem.editAccountHolderName(editAccountHolderNameNumber);
                    break;
                case 7:
                    System.out.println("\u001B[31m"+"See You Again Soon! Hope We Were Helpful."+"\u001B[36m");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
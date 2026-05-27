package bankingsystem;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class BankingSystem {
 
    static final String STAFF_USERNAME = "Pearson";
    static final String STAFF_PASSWORD = "staff1905";
 
    static class Customer {
        String firstName;
        String username;
        String password;
        String accountType;
        double balance;
 
        Customer(String firstName, String username, String password, String accountType, double balance) {
            this.firstName  = firstName;
            this.username   = username;
            this.password   = password;
            this.accountType = accountType;
            this.balance    = balance;
        }
    }
 
    static List<Customer> customers = new ArrayList<>();
    static Customer loggedInCustomer = null;
    static boolean running = true;
 
    static Scanner scanner = new Scanner(System.in);
 
    static {
        customers.add(new Customer("Thembelihle", "Sithole", "1406", "Savings", 50000));
        customers.add(new Customer("Kwandokuhle",  "Sithole", "1904", "Cheque",  50000));
    }
 
    public static void main(String[] args) {
        while (running) {
            mainMenu();
        }
        scanner.close();
    }
 
    static void mainMenu() {
        System.out.println("**********WELCOME**********");
        System.out.println("Select An Option:");
        System.out.println("1. LOGIN AS CUSTOMER");
        System.out.println("2. LOGIN AS STAFF");
        System.out.println("3. EXIT SYSTEM");
        
        int option = readInt();
 
        switch (option) {
            case 1 -> customerLogin();
            case 2 -> staffLogin();
            case 3 -> {
                System.out.println("Thank you for banking with us. Goodbye!");
                running = false;
            }
            default -> System.out.println("INVALID OPTION, TRY AGAIN.");
        }
    }
 
    static void staffMenu() {
        boolean inStaffMenu = true;
        while (inStaffMenu) {
            System.out.println("*****STAFF MENU*****");
            System.out.println("0. VIEW ALL CUSTOMERS");
            System.out.println("1. LOGOUT");
 
            int option = readInt();
            switch (option) {
                case 1 -> viewAllCustomers();
                case 0 -> inStaffMenu = false;
                default -> System.out.println("INVALID OPTION, TRY AGAIN.");
            }
        }
    }
 
    static void customerMenu() {
        boolean inCustomerMenu = true;
        while (inCustomerMenu) {
            System.out.println("***** CUSTOMER MENU — Welcome, " + loggedInCustomer.firstName + " *****");
            System.out.println("1. WITHDRAW");
            System.out.println("2. DEPOSIT");
            System.out.println("3. CHECK BALANCE");
            System.out.println("0. LOGOUT");
 
            int option = readInt();
            switch (option) {
                case 1 -> withdraw();
                case 2 -> deposit();
                case 3 -> checkBalance();
                case 0 -> {
                    loggedInCustomer = null;
                    inCustomerMenu = false;
                    System.out.println("Logged in successfully.");
                }
                default -> System.out.println("INVALID OPTION, TRY AGAIN.");
            }
        }
    }
 
    static void staffLogin() {
        System.out.println("\n*****STAFF LOGIN*****");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();
 
        if (STAFF_USERNAME.equals(username) && STAFF_PASSWORD.equals(password)) {
            System.out.println("LOGIN SUCCESSFUL. Welcome, Staff!");
            staffMenu();
        } else {
            System.out.println("INVALID DETAILS. TRY AGAIN.");
        }
    }
 
    static void customerLogin() {
        System.out.println("**********CUSTOMER LOGIN**********");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();
 
        loggedInCustomer = null;
        for (Customer c : customers) {
            if (c.username.equals(username) && c.password.equals(password)) {
                loggedInCustomer = c;
                break;
            }
        }
 
        if (loggedInCustomer != null) {
            System.out.println("LOGIN SUCCESSFUL. Welcome, " + loggedInCustomer.firstName + "!");
            customerMenu();
        } else {
            System.out.println("INVALID DETAILS. TRY AGAIN.");
        }
    }
 
    static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = readDouble();
 
        if (amount <= 0) {
            System.out.println("Amount must be a positive number.");
        } else if (amount > loggedInCustomer.balance) {
            System.out.println("Insufficient funds.");
        } else {
            loggedInCustomer.balance -= amount;
            System.out.printf("Withdrawal successful. New balance: R%.2f%n",
                    loggedInCustomer.balance);
        }
    }
 
    static void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = readDouble();
 
        if (amount <= 0) {
            System.out.println("Amount must be a positive number.");
        } else {
            loggedInCustomer.balance += amount;
            System.out.printf("Deposit successful. New balance: R%.2f%n",
                    loggedInCustomer.balance);
        }
    }
 
    static void checkBalance() {
        System.out.println("**** ACCOUNT SUMMARY ****");
        System.out.println("Account Holder : " + loggedInCustomer.firstName);
        System.out.println("Account Type   : " + loggedInCustomer.accountType);
        System.out.printf ("Balance        : R", loggedInCustomer.balance);
    }
 
    static void viewAllCustomers() {
        System.out.println("**** ALL CUSTOMERS ****");
        for (Customer c : customers) {
            System.out.printf("R", c.firstName, c.accountType, c.balance);
        }
    }
 
    static int readInt() {
        while (true) {
            try {
                System.out.print("> ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }
 
    static double readDouble() {
        while (true) {
            try {
                System.out.print("> ");
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }
}
import java.util.Scanner;

public class Main {

    // Class to represent the ATM
    static class ATM {
        private double balance;
        private int pin;
        private boolean isAuthenticated;

        // Constructor to initialize balance and PIN
        public ATM(double balance, int pin) {
            this.balance = balance;
            this.pin = pin;
            this.isAuthenticated = false;
        }

        // Method to authenticate the user
        public boolean authenticate(int enteredPin) {
            if (enteredPin == pin) {
                isAuthenticated = true;
                System.out.println("Authentication successful!");
                return true;
            } else {
                System.out.println("Incorrect PIN. Access denied.");
                return false;
            }
        }

        // Method to display the ATM menu
        public void displayMenu() {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Change PIN");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
        }

        // Method to check account balance
        public void checkBalance() {
            if (isAuthenticated) {
                System.out.println("Your current balance is: $" + balance);
            } else {
                System.out.println("Please authenticate first.");
            }
        }

        // Method to deposit money
        public void deposit(double amount) {
            if (isAuthenticated) {
                if (amount > 0) {
                    balance += amount;
                    System.out.println("$" + amount + " deposited successfully.");
                    System.out.println("New balance: $" + balance);
                } else {
                    System.out.println("Invalid amount. Please enter a positive value.");
                }
            } else {
                System.out.println("Please authenticate first.");
            }
        }

        // Method to withdraw money
        public void withdraw(double amount) {
            if (isAuthenticated) {
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    System.out.println("$" + amount + " withdrawn successfully.");
                    System.out.println("Remaining balance: $" + balance);
                } else if (amount > balance) {
                    System.out.println("Insufficient funds. Your balance is $" + balance);
                } else {
                    System.out.println("Invalid amount. Please enter a positive value.");
                }
            } else {
                System.out.println("Please authenticate first.");
            }
        }

        // Method to change PIN
        public void changePin(int newPin) {
            if (isAuthenticated) {
                pin = newPin;
                System.out.println("PIN changed successfully.");
            } else {
                System.out.println("Please authenticate first.");
            }
        }
    }

    // Main method to run the ATM simulation
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM(1000.0, 1234); // Initial balance: $1000, Default PIN: 1234

        System.out.println("===== WELCOME TO THE ATM =====");

        // Authenticate the user
        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();
        if (!atm.authenticate(enteredPin)) {
            System.out.println("Exiting...");
            return;
        }

        boolean isRunning = true;
        while (isRunning) {
            atm.displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter new PIN: ");
                    int newPin = scanner.nextInt();
                    atm.changePin(newPin);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
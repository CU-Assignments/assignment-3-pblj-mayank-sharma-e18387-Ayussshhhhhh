import java.util.Scanner;

class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class ATM {
    private int correctPin = 1234;  
    private double balance = 3000;  

    public void verifyPin(int enteredPin) throws InvalidPinException {
        if (enteredPin != correctPin) {
            throw new InvalidPinException("Error: Invalid PIN.");
        }
    }


    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Error: Insufficient balance. Current Balance: " + balance);
        }
        balance -= amount;
        System.out.println("Withdrawal successful! Remaining Balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }
}

public class ATMWithdrawal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        try {

            System.out.print("Enter PIN: ");
            int enteredPin = scanner.nextInt();

            atm.verifyPin(enteredPin);

            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();

            atm.withdraw(amount);

        } catch (InvalidPinException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter a numeric value.");
        } finally {
            // Display balance even if an exception occurs
            System.out.println("Current Balance: " + atm.getBalance());
            scanner.close();
        }
    }
}

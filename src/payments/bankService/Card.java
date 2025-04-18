package payments.bankService;

public class Card {
    private final String number;
    private final String pin;
    private double balance;

    public Card(String number, String pin, double balance) {
        this.number = number;
        this.pin = pin;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public String getPin() {
        return pin;
    }

    public boolean performWriteOff(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

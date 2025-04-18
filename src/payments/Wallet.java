package payments;

public class Wallet implements PaymentListener {
    private double balance;

    public Wallet(double amount) {
        this.balance = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void increaseAmount(double amount) {
        this.balance += amount;
    }

    public boolean decreaseAmount(double amount) {
        this.balance -= amount;
        return false;
    }
}

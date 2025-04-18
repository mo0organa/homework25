package model;

public class Wallet {
    private double amount;

    public Wallet(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void increaseAmount(double amount) {
        this.amount += amount;
    }

    public boolean decreaseAmount(double amount) {
        this.amount -= amount;
        return false;
    }
}

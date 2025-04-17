package model;

public class Wallet {
    private int amount;

    public Wallet(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void increaseAmount(int amount) {
        this.amount += amount;
    }

    public boolean decreaseAmount(int amount) {
        this.amount -= amount;
        return false;
    }
}

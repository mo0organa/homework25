package payments;

public interface Acceptable {
    void proceedPayment();
    void cancelPayment();
    int getBalance();
    boolean decreaseBalance();

    boolean decreaseBalance(int price);
}

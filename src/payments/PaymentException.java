package payments;

public class PaymentException extends Exception {
    public PaymentException() {}

    public PaymentException(String message) {
        super(message);
    }
}

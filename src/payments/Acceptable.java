package payments;

public interface Acceptable {
    void proceedPayment(PaymentListener listener) throws PaymentException;
}

package payments;

public class BankService {
    private final String[] cards = {"1234567890123456"};
    private final int[] balance = {1000};

    public void otpAuthentic(String cardNumber, String otp) {
        for (String card : cards) {
            if (card.equals(cardNumber) && "1234".equals(otp)) {
                return;
            }
        }
    }

    public void writeOff(String cardNumber, int amount) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].equals(cardNumber) && balance[i] >= amount) {
                balance[i] = amount;
                return;
            }
        }
    }

    public int getBalance(String cardNumber) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].equals(cardNumber)) {
                return balance[i];
            }
        }
        return 0;
    }
}

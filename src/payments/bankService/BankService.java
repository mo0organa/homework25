package payments.bankService;

public class BankService {
    private final Card[] cards;

    public BankService() {
        cards = new Card[]{
                new Card("5450204275038729", "1234", 1000),
                new Card("5536056489925903", "0000", 0),
                new Card("1234567890123456", "1234", 10000),
        };
    }

    public boolean isExist(String cardNumber) {
        try {
            getCard(cardNumber);
            return true;
        } catch (CardNotFoundException ex) {
            return false;
        }
    }

    public Card getCard(String cardNumber) throws CardNotFoundException {
        for (Card card : cards) {
            if (card.getNumber().equals(cardNumber)) {
                return card;
            }
        }

        throw new CardNotFoundException();
    }

    public boolean PerformTransaction(String cardNumber, String pin, double amount) {
        try {
            Card card = getCard(cardNumber);
            if (!card.getPin().equals(pin)) {
                return false;
            }

            return card.performWriteOff(amount);

        } catch (CardNotFoundException e) {
            return false;
        }
    }
}

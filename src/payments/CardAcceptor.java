package payments;
import payments.bankService.BankService;
import java.util.Scanner;

public class CardAcceptor implements Acceptable {
    private final BankService bankService = new BankService();
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void proceedPayment(PaymentListener listener) throws PaymentException {
        String cardNumber = readCardNumber();
        String pin = readPin();
        double amount = readAmount();

        if (bankService.isExist(cardNumber)) {
            if (bankService.PerformTransaction(cardNumber, pin, amount)) {
                System.out.println("Транзакция успешно проведена: списано " + amount);
                listener.increaseAmount(amount);
            }
            else {
                throw new PaymentException("Неверный PIN или недостаточно средств на карте!");
            }
        }
        else {
            throw new PaymentException("Карта с таким номером не найдена!");
        }
    }

    private String readNumber(String fieldName, int length) {
        while (true) {
            System.out.printf("Введите %s %d: ", fieldName, length);
            String cardNumber = sc.nextLine().trim();

            if (cardNumber.isEmpty()) {
                System.out.println("Поле ввода не может быть пустым!");
                continue;
            }
            if (cardNumber.length() != length) {
                System.out.printf("%s должен состоять из %d цифр!%n", fieldName, length);
                continue;
            }
            boolean isValid = true;
            for (char c : cardNumber.toCharArray()) {
                if (!Character.isDigit(c)) {
                    System.out.printf("%s должен содержать только цифры!", fieldName);
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                return cardNumber;
            }
        }
    }

    private String readCardNumber() {
        return readNumber("номер карты", 16);
    }

    private String readPin() {
        return readNumber("PIN", 4);
    }

    private double readAmount() throws PaymentException {
        while (true) {
            System.out.println("Введите сумму для списания с вашей карты: ");
            try {
                return sc.nextDouble();
            } catch (Exception e) {
                System.out.println("Неверно введенные данные!");
            }
        }
    }
}

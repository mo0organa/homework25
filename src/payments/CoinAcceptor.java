package payments;

import java.util.Scanner;

public class CoinAcceptor implements Acceptable {
    private final static int[] coins = {1, 3, 5, 10, 20, 25, 50};
    private final Scanner sc = new Scanner(System.in);


    public void printCoinOptions() {
        for (int i = 0; i < coins.length; i++) {
            System.out.print(coins[i]);
            if (i < coins.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public void proceedPayment(PaymentListener listener) {
        double sum = 0;

        while (true) {
            System.out.print("Вставьте монету или введите 'q' для завершения ввода: ");
            printCoinOptions();

            String input = sc.nextLine().trim();
            if ("q".equalsIgnoreCase(input)) {
                System.out.printf("Ввод монет завершён, всего внесено: %f%n", sum);
                break;
            }

            try {
                int coin = Integer.parseInt(input);
                if (isAllowed(coin)) {
                    sum += coin;
                    System.out.printf("Принято: %d. Ваш баланс: %.2f%n", coin, sum);
                } else {
                    System.out.printf("Монета номиналом %d не принимается.%n", coin);
                }
            } catch (NumberFormatException ex) {
                System.out.println("Некорректный ввод! Введите число или 'q' для завершения ввода.");
            }
        }
        listener.increaseAmount(sum);
    }

    private boolean isAllowed(int coin) {
        for (int num : coins) {
            if (num == coin) {
                return true;
            }
        }
        return false;
    }
}

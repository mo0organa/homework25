package payments;

import java.util.Scanner;

public class CoinAcceptor implements Acceptable {
    private final static int[] coins = {1, 3, 5, 10, 20, 25, 50};
    private final Scanner sc = new Scanner(System.in);
    private int balance = 0;


    @Override
    public void proceedPayment() {
        double sum = 0;
        while (true) {
            System.out.print("Вставьте монету или введите 'q' для завершения ввода ");
            for (int i = 0; i < coins.length; i++) {
                System.out.println(coins[i]);
                if (i < coins.length - 1) {
                    System.out.println(", ");
                }
            }
            System.out.print("): ");

            String input = sc.nextLine().trim();
            if ("q".equalsIgnoreCase(input)) {
                System.out.println("Ввод монет завершен, вы внесли всего монет: " + sum);
                break;
            }

            try {
                int coin = Integer.parseInt(input);
                if (isAllowed(coin)) {
                    sum += coin;
                    System.out.println("Принято:" + coin + "Текущий баланс пополнения: " + sum);
                } else {
                    System.out.println("Монета номиналом " + coin + " не принимается. Допустимые: 1, 3, 5, 10, 20, 25, 50.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Некорректный ввод. Введите число (номинал монеты) или 'q' для выхода.");
            }
        }
    }

    @Override
    public void cancelPayment() {
        balance = 0;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public boolean decreaseBalance() {
        return false;
    }

    @Override
    public boolean decreaseBalance(int price) {
        if (balance >= price) {
            balance -= price;
            return true;
        }
        return false;
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

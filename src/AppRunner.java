import enums.ActionLetter;
import model.*;
import payments.Acceptable;
import payments.CoinAcceptor;
import util.UniversalArray;
import util.UniversalArrayImpl;

import java.util.Scanner;

public class AppRunner {

    private final UniversalArray<Product> products = new UniversalArrayImpl<>();
    private final Acceptable paymentAcceptor = new CoinAcceptor();
    private static boolean isExit = false;
    private final Wallet wallet;

    private AppRunner() {
        products.addAll(new Product[]{
                new Water(ActionLetter.B, 20),
                new CocaCola(ActionLetter.C, 50),
                new Soda(ActionLetter.D, 30),
                new Snickers(ActionLetter.E, 80),
                new Mars(ActionLetter.F, 80),
                new Pistachios(ActionLetter.G, 130)
        });
        this.wallet = new Wallet(100);
    }

    public static void run() {
        AppRunner app = new AppRunner();
        while (!isExit) {
            app.startSimulation();
        }
    }

    private void startSimulation() {
        print("В автомате доступны:");
        showProducts(products);

        print("Монет на сумму: " + wallet.getAmount());
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        allowProducts.addAll(getAllowedProducts().toArray());
        chooseAction(allowProducts);
    }

    private UniversalArray<Product> getAllowedProducts() {
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if (wallet.getAmount() >= products.get(i).getPrice()) {
                allowProducts.add(products.get(i));
            }
        }
        return allowProducts;
    }

    private void chooseAction(UniversalArray<Product> products) {
        print(" a - Пополнить баланс");
        showActions(products);
        print(" h - Выйти");
        String action = fromConsole();

        if (action.length() != 1) {
            print("Некорректная команда. Введите одну букву из доступных вариантов.");
            return;
        }
        action = action.substring(0, 1);

        if ("h".equalsIgnoreCase(action)) {
            isExit = true;
            return;
        }
        if ("a".equalsIgnoreCase(action)) {
            wallet.increaseAmount(paymentAcceptor.proceedPayment());
            return;
        }
        try {
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                if (product.getActionLetter().getValue().equals(action)) {
                    if (wallet.decreaseAmount(product.getPrice())) {
                        print("Вы купили " + product.getName());
                    } else{
                        print("Недостаточно средств для покупки " + product.getName());
                    }
                    return;
                }
            }
        } catch (IllegalArgumentException e) {
            print("Недопустимая буква. Попробуйте еще раз.");
            chooseAction(products);
        }
    }

    private void showActions(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(String.format(" %s - %s", products.get(i).getActionLetter().getValue(), products.get(i).getName()));
        }
    }

    private String fromConsole() {
        return new Scanner(System.in).nextLine();
    }

    private void showProducts(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(products.get(i).toString());
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }
}

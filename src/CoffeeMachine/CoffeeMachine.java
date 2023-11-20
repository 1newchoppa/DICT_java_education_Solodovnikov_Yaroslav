package CoffeeMachine;
import java.util.Scanner;

public class CoffeeMachine {

    private int money = 550;
    private int water = 400;
    private int milk = 540;
    private int coffeeBeans = 120;
    private int disposableCups = 9;

    public void buyCoffee() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back – to main menu:");
        String choice = scanner.nextLine();

        if (choice.equals("back")) {
            return;
        }

        int waterNeeded = 0, milkNeeded = 0, beansNeeded = 0, cost = 0;

        switch (choice) {
            case "1": // Espresso
                waterNeeded = 250;
                beansNeeded = 16;
                cost = 4;
                break;
            case "2": // Latte
                waterNeeded = 350;
                milkNeeded = 75;
                beansNeeded = 20;
                cost = 7;
                break;
            case "3": // Cappuccino
                waterNeeded = 200;
                milkNeeded = 100;
                beansNeeded = 12;
                cost = 6;
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        if (water >= waterNeeded && milk >= milkNeeded && coffeeBeans >= beansNeeded && disposableCups >= 1) {
            System.out.println("I have enough resources, making you a coffee!");
            water -= waterNeeded;
            milk -= milkNeeded;
            coffeeBeans -= beansNeeded;
            money += cost;
            disposableCups--;
        } else {
            System.out.println("Sorry, not enough resources!");
        }
    }

    public void fillIngredients() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add:");
        int addedWater = scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        int addedMilk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int addedBeans = scanner.nextInt();
        System.out.println("Write how many disposable cups do you want to add:");
        int addedCups = scanner.nextInt();

        water += addedWater;
        milk += addedMilk;
        coffeeBeans += addedBeans;
        disposableCups += addedCups;
    }

    public void takeMoney() {
        System.out.println("I gave you UAH " + money);
        money = 0;
    }

    public void printStatus() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffeeBeans + " g of coffee beans");
        System.out.println(disposableCups + " disposable cups");
        System.out.println("UAH  " + money + " of money");
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
            String action = scanner.nextLine();

            switch (action) {
                case "buy":
                    coffeeMachine.buyCoffee();
                    break;
                case "fill":
                    coffeeMachine.fillIngredients();
                    break;
                case "take":
                    coffeeMachine.takeMoney();
                    break;
                case "remaining":
                    coffeeMachine.printStatus();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid action");
                    break;
            }
        }
    }
}

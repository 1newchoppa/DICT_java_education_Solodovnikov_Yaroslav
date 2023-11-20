package CoffeeMachine;
import java.util.Scanner;

public class CoffeeMachine {

    public void makeCoffee() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write how many ml of water the coffee machine has:");
        int waterAvailable = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        int milkAvailable = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int coffeeBeansAvailable = scanner.nextInt();

        System.out.println("Write how many cups of coffee you will need:");
        int cupsNeeded = scanner.nextInt();

        int producedCups = Math.min(Math.min(waterAvailable / 200,milkAvailable / 50), coffeeBeansAvailable / 15);

        if (producedCups == cupsNeeded){
            System.out.println("Yes, I can make that amount of coffee");
        }

        else if (producedCups > cupsNeeded){
            System.out.println("Yes, I can make that amount of coffee (and even " + (producedCups - cupsNeeded) + " more than that)");
        }

        else {
            System.out.println("No, I can make only " + producedCups + " cups of coffee");
        }

    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.makeCoffee();
    }

}
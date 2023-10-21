package ChatBot;
import java.util.Scanner;

public class ChatBot {

    public static void main(String[] args) {
        System.out.println("Hello! My name is Steve.");
        System.out.println("I was created in 2023.");
        System.out.println("Please,remind me your name");

        Scanner scanner = new Scanner(System.in);

        String userName = scanner.nextLine();

        System.out.println("What a great name you have, " + userName + "!");
        System.out.println("Let me guess your age.\n" +
                "Enter remainders of dividing your age by 3, 5 and 7.");

        int remainder3 = scanner.nextInt();
        int remainder5 = scanner.nextInt();
        int remainder7 = scanner.nextInt();

        int yourAge = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;

        System.out.println("Your age is " + yourAge + " that`s a good time to start programming!");

        System.out.println("Now I will prove to you that I can count to any number you want!");

        int userInp = scanner.nextInt();

        for (int i = 0; i <= userInp; i++) {
            System.out.println(i + " !");
        }

        System.out.println("Let`s test your programming knowledge");
        System.out.println("What does the 'System.out.println()' method do in Java?");
        System.out.println("1. Reads user input");
        System.out.println("2. Prints text to the console");
        System.out.println("3. Performs a mathematical calculation");
        System.out.println("4. Creates a new object");

        int correctAnswer = 2;
        int userAnswer;

        do {
            System.out.println("Enter the number of your answer:");
            userAnswer = scanner.nextInt();

            if (userAnswer == correctAnswer) {
                System.out.println("Congratulations, you`re right!");
            } else  {
                System.out.println("Sorry, your answer is incorrect. Try again.");
            }
        } while (userAnswer != correctAnswer);

        System.out.println("Goodbye, have a nice day!");
    }
}

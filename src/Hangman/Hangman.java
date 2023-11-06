package Hangman;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {

        String correctAnswer = "python";

        System.out.println("HANGMAN");
        System.out.print("Guess the word:>");
        Scanner scanner = new Scanner(System.in);

        String userAnswer = scanner.nextLine();

        if (userAnswer.equals(correctAnswer)) {
            System.out.println("You survived!");
        } else {
            System.out.println("You lost!");
        }
    }
}







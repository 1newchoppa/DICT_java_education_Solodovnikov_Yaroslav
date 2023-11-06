package Hangman;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

public class Hangman {
    public static void main(String[] args) {

        List<String> list = List.of("python","java","javascript","kotlin");

        Random random = new Random();
        int randomIndex = random.nextInt(list.size());

        String randomLanguage = list.get(randomIndex);

        System.out.println(randomLanguage);

        String substring = randomLanguage.substring(0,2);
        String maskedPart = "-".repeat(randomLanguage.length() - 2);

        System.out.println(substring + maskedPart);

        System.out.println("HANGMAN");
        System.out.print("Guess the word:>");
        Scanner scanner = new Scanner(System.in);

        String userAnswer = scanner.nextLine();

        if (userAnswer.equals(randomLanguage)) {
            System.out.println("You survived!");
        } else {
            System.out.println("You lost!");
        }
    }
}







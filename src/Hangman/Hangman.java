package Hangman;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

public class Hangman {
    public static void main(String[] args) {

        int tries = 8;

        List<String> list = List.of("python", "java", "javascript", "kotlin");

        Random random = new Random();
        int randomIndex = random.nextInt(list.size());

        String randomLanguage = list.get(randomIndex);

        String maskedWord = "-".repeat(randomLanguage.length());

        StringBuilder guessedLetters = new StringBuilder();


        Scanner scanner = new Scanner(System.in);

        char userGuess;

        while (tries > 0) {
            System.out.println(tries);
            System.out.println(maskedWord);
            System.out.print("Guess the word:>");
            String input = scanner.next();

            if (input.length() != 1) {
                System.out.println("You should input a single letter.");
                continue;
            }


            userGuess = input.charAt(0);

            if (!Character.isLowerCase(userGuess)){
                System.out.println("Please enter a lowercase English letter.");
                continue;
            }

            if (guessedLetters.toString().contains(String.valueOf(userGuess))) {
                System.out.println("You`ve already guessed this letter.");
                continue;
            }

            guessedLetters.append(userGuess);

            if (randomLanguage.contains(String.valueOf(userGuess))) {
                StringBuilder newMaskedWord = new StringBuilder(maskedWord);
                for (int i = 0; i < randomLanguage.length(); i++) {
                    if (randomLanguage.charAt(i) == userGuess) {
                        newMaskedWord.setCharAt(i, userGuess);
                    }
                }
                maskedWord = newMaskedWord.toString();
            } else {
                tries--;
                System.out.println("That letter doesnt appear in the word.");
            }


            if (!maskedWord.contains("-")) {
                System.out.println("Thanks for playing!\n" +
                        "Well see how well you did in the next stage");
                break;
            }
        }

        if (tries == 0) {
            System.out.println("Thanks for playing!\n" +
                    "Well see how well you did in the next stage");
        }
    }
}








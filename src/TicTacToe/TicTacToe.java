package TicTacToe;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };

        System.out.print(">");
        String userInput = scanner.nextLine();

        if (userInput.length() != 9) {
            System.out.println("Рядок повинен містити 9 символів.");
            return;
        }

        int index = 0;

        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                char symbol = userInput.charAt(index);

                if (symbol != 'X' && symbol != '0' && symbol != '_') {
                    System.out.println("Допустимі символи: X, 0, _");
                    return;
                }

                board[i][j] = symbol;
                System.out.print(board[i][j] + " ");
                index++;
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}


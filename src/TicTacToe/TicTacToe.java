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
        int xCount = 0;
        int oCount = 0;

        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                char symbol = userInput.charAt(index);

                if (symbol != 'X' && symbol != 'O' && symbol != '_') {
                    System.out.println("Допустимі символи: X, O, _");
                    return;
                }

                if (symbol == 'X') {
                    xCount++;
                } else if (symbol == 'O') {
                    oCount++;
                }

                board[i][j] = symbol;
                System.out.print(board[i][j] + " ");
                index++;
            }
            System.out.println("|");
        }
        System.out.println("---------");

        while (true) {
            System.out.print("Enter the coordinates: ");
            String[] coordinates = scanner.nextLine().split(" ");

            if (coordinates.length != 2) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int x, y;

            try {
                x = Integer.parseInt(coordinates[0]);
                y = Integer.parseInt(coordinates[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            x--;
            y--;

            if (board[x][y] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            board[x][y] = 'X';
            break;
        }

        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}

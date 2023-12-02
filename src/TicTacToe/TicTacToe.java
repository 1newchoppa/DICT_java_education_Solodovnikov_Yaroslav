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

        if (Math.abs(xCount - oCount) > 1) {
            System.out.println("Impossible");
            return;
        }

        boolean xWins = checkWin(board, 'X');
        boolean oWins = checkWin(board, 'O');

        if (xWins && oWins) {
            System.out.println("Impossible");
        } else if (xWins) {
            System.out.println("X wins");
        } else if (oWins) {
            System.out.println("O wins");
        } else if (xCount + oCount == 9) {
            System.out.println("Draw");
        } else {
            System.out.println("Game not finished");
        }
    }

    public static boolean checkWin(char[][] board, char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }

            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true;
            }
        }

        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }

        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }

        return false;
    }
}

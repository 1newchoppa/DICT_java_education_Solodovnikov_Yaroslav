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

        int xCount = 0;
        int oCount = 0;
        boolean isX = true;

        while (true) {
            System.out.println("---------");
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j] + " ");
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

                board[x][y] = isX ? 'X' : 'O';
                isX = !isX;
                break;
            }

            if (checkWin(board, 'X')) {
                System.out.println("X wins");
                break;
            } else if (checkWin(board, 'O')) {
                System.out.println("O wins");
                break;
            } else if (isDraw(board)) {
                System.out.println("Draw");
                break;
            }
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

    public static boolean isDraw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }
}

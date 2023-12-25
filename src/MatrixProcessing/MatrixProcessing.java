package MatrixProcessing;

import java.util.Scanner;

public class MatrixProcessing {
    public int[][] readMatrix() {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        return matrix;
    }

    public int[][] addMatrices(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int rowsB = matrixB.length;
        int colsB = matrixB[0].length;

        if (rowsA != rowsB || colsA != colsB) {
            System.out.println("ERROR");
            return null;
        }

        int[][] result = new int[rowsA][colsA];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }

        return result;
    }

    public int[][] multiplyByConstant(int[][] matrix, int constant) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j] * constant;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MatrixProcessing matrixProcessing = new MatrixProcessing();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter matrix:");
        int[][] matrix = matrixProcessing.readMatrix();

        System.out.println("Enter constant:");
        int constant = scanner.nextInt();

        int[][] multipliedMatrix = matrixProcessing.multiplyByConstant(matrix, constant);

        System.out.println("Result:");
        for (int[] row : multipliedMatrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

}

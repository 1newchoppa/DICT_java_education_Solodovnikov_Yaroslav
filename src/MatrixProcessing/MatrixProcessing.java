package MatrixProcessing;

import java.util.Scanner;

public class MatrixProcessing {

    public static Number[][] readMatrix(String matrixName) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows and columns for matrix " + matrixName + ":");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        System.out.println("Enter matrix " + matrixName + ":");
        Number[][] matrix = new Number[rows][cols];

        scanner.nextLine();
        for (int i = 0; i < rows; i++) {
            System.out.println("Enter row " + (i + 1) + " for matrix " + matrixName + ":");
            String[] values = scanner.nextLine().split("\\s+");
            for (int j = 0; j < cols; j++) {
                if (values[j].contains(".")) {
                    matrix[i][j] = Double.parseDouble(values[j].replace(',', '.'));
                } else {
                    matrix[i][j] = Integer.parseInt(values[j]);
                }
            }
        }

        return matrix;
    }

    public static Number[][] multiplyByConstant(Number[][] matrix, Number constant) {
        Number[][] result = new Number[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] instanceof Double) {
                    result[i][j] = (double) matrix[i][j] * constant.doubleValue();
                } else {
                    result[i][j] = (int) matrix[i][j] * constant.intValue();
                }
            }
        }
        return result;
    }

    public static void printMatrix(Number[][] matrix) {
        for (Number[] row : matrix) {
            for (Number element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static Number[][] multiplyMatrices(Number[][] matrixA, Number[][] matrixB) {
        if (matrixA[0].length != matrixB.length) {
            return null;
        }

        Number[][] result = new Number[matrixA.length][matrixB[0].length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                result[i][j] = 0;
                for (int k = 0; k < matrixA[0].length; k++) {
                    if (matrixA[i][k] instanceof Double || matrixB[k][j] instanceof Double) {
                        result[i][j] = result[i][j].doubleValue() +
                                matrixA[i][k].doubleValue() * matrixB[k][j].doubleValue();
                    } else {
                        result[i][j] = result[i][j].intValue() +
                                matrixA[i][k].intValue() * matrixB[k][j].intValue();
                    }
                }
            }
        }
        return result;
    }

    public static Number[][] transposeMatrix(Number[][] inputMatrix, int choice) {
        int rows = inputMatrix.length;
        int cols = inputMatrix[0].length;
        Number[][] result = new Number[cols][rows];

        switch (choice) {
            case 1:
                for (int i = 0; i < cols; i++) {
                    for (int j = 0; j < rows; j++) {
                        result[i][j] = inputMatrix[j][i];
                    }
                }
                break;
            case 2:
                for (int i = 0; i < cols; i++) {
                    for (int j = 0; j < rows; j++) {
                        result[i][j] = inputMatrix[rows - j - 1][cols - i - 1];
                    }
                }
                break;
            case 3:
                for (int i = 0; i < cols; i++) {
                    for (int j = 0; j < rows; j++) {
                        result[i][j] = inputMatrix[i][rows - j - 1];
                    }
                }
                break;
            case 4:
                for (int i = 0; i < cols; i++) {
                    for (int j = 0; j < rows; j++) {
                        result[i][j] = inputMatrix[cols - i - 1][j];
                    }
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }

        return result;
    }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("1. Add matrices\n2. Multiply matrix by a constant\n3. Multiply matrices\n4. Transpose matrix\n0. Exit");
            System.out.print("Your choice: > ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Number[][] matrixA = readMatrix("A");
                    Number[][] matrixB = readMatrix("B");
                    if (matrixA.length == matrixB.length && matrixA[0].length == matrixB[0].length) {
                        Number[][] sum = new Number[matrixA.length][matrixA[0].length];
                        for (int i = 0; i < matrixA.length; i++) {
                            for (int j = 0; j < matrixA[0].length; j++) {
                                if (matrixA[i][j] instanceof Double || matrixB[i][j] instanceof Double) {
                                    sum[i][j] = (double) matrixA[i][j] + (double) matrixB[i][j];
                                } else {
                                    sum[i][j] = (int) matrixA[i][j] + (int) matrixB[i][j];
                                }
                            }
                        }
                        System.out.println("The result is:");
                        printMatrix(sum);
                    } else {
                        System.out.println("Matrices cannot be added.");
                    }
                    break;

                case 2:
                    Number[][] matrix = readMatrix("");
                    System.out.print("Enter constant: > ");
                    Number constant;
                    if (scanner.hasNextInt()) {
                        constant = scanner.nextInt();
                    } else {
                        constant = scanner.nextDouble();
                    }
                    Number[][] result = multiplyByConstant(matrix, constant);
                    System.out.println("The result is:");
                    printMatrix(result);
                    break;

                case 3:
                    Number[][] matrix1 = readMatrix("A");
                    Number[][] matrix2 = readMatrix("B");
                    Number[][] product = multiplyMatrices(matrix1, matrix2);
                    if (product == null) {
                        System.out.println("Matrices cannot be multiplied.");
                    } else {
                        System.out.println("The result is:");
                        printMatrix(product);
                    }
                    break;

                case 4:
                    System.out.println("1. Main diagonal\n2. Side diagonal\n3. Vertical line\n4. Horizontal line");
                    System.out.print("Your choice: > ");
                    int transposeChoice = scanner.nextInt();
                    Number[][] inputMatrix = readMatrix("");
                    Number[][] transposedMatrix = transposeMatrix(inputMatrix, transposeChoice);
                    System.out.println("The result is:");
                    printMatrix(transposedMatrix);
                    break;



            }
        } while (choice != 0);
    }
}

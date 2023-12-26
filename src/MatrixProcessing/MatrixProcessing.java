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

    public static long calculateDeterminant(Number[][] determinantMatrix) {
        int rows = determinantMatrix.length;
        int cols = determinantMatrix[0].length;

        if (rows != cols) {
            return Long.MIN_VALUE;
        }

        if (rows == 1) {
            return determinantMatrix[0][0].longValue();
        }

        if (rows == 2) {
            return determinantMatrix[0][0].longValue() * determinantMatrix[1][1].longValue() -
                    determinantMatrix[0][1].longValue() * determinantMatrix[1][0].longValue();
        }

        long determinant = 0;
        for (int i = 0; i < cols; i++) {
            Number[][] subMatrix = new Number[rows - 1][cols - 1];
            for (int j = 1; j < rows; j++) {
                for (int k = 0, l = 0; k < cols; k++) {
                    if (k == i) {
                        continue;
                    }
                    subMatrix[j - 1][l++] = determinantMatrix[j][k];
                }
            }
            determinant += Math.pow(-1, i) * determinantMatrix[0][i].longValue() * calculateDeterminant(subMatrix);
        }
        return determinant;
    }

    public static Number[][] calculateAdjointMatrix(Number[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (rows != cols) {
            return null;
        }

        Number[][] adjoint = new Number[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Number[][] subMatrix = new Number[rows - 1][cols - 1];
                int subRow = 0, subCol = 0;
                for (int k = 0; k < rows; k++) {
                    if (k == i) {
                        continue;
                    }
                    subCol = 0;
                    for (int l = 0; l < cols; l++) {
                        if (l != j) {
                            subMatrix[subRow][subCol] = matrix[k][l];
                            subCol++;
                        }
                    }
                    subRow++;
                }
                long determinant = calculateDeterminant(subMatrix);
                adjoint[i][j] = (i + j) % 2 == 0 ? determinant : -determinant;
            }
        }

        return transposeMatrix(adjoint, 1);
    }

    public static Number[][] inverseMatrix(Number[][] inversedMatrix) {
        int rows = inversedMatrix.length;
        int cols = inversedMatrix[0].length;

        if (rows != cols) {
            System.out.println("This matrix is not square. Inverse matrix cannot be found.");
            return null;
        }

        long determinant = calculateDeterminant(inversedMatrix);
        if (determinant == 0) {
            System.out.println("This matrix doesn't have an inverse.");
            return null;
        }

        Number[][] extendedMatrix = new Number[rows][cols * 2];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(inversedMatrix[i], 0, extendedMatrix[i], 0, cols);
            for (int j = cols; j < cols * 2; j++) {
                if (j - cols == i) {
                    extendedMatrix[i][j] = 1;
                } else {
                    extendedMatrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            Number pivot = extendedMatrix[i][i];
            for (int j = 0; j < cols * 2; j++) {
                extendedMatrix[i][j] = extendedMatrix[i][j].doubleValue() / pivot.doubleValue();
            }
            for (int k = 0; k < rows; k++) {
                if (k != i) {
                    Number factor = extendedMatrix[k][i];
                    for (int j = 0; j < cols * 2; j++) {
                        extendedMatrix[k][j] = extendedMatrix[k][j].doubleValue() - factor.doubleValue() * extendedMatrix[i][j].doubleValue();
                    }
                }
            }
        }

        Number[][] inverse = new Number[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(extendedMatrix[i], cols, inverse[i], 0, cols);
        }

        return inverse;
    }

    public static void printRoundedMatrix(Number[][] matrix) {
        for (Number[] row : matrix) {
            for (Number element : row) {
                System.out.printf("%.2f ", element.doubleValue());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("1. Add matrices\n2. Multiply matrix by a constant\n3. Multiply matrices\n4. Transpose matrix\n5. Calculate a determinant\n6. Inverse matrix\n0. Exit");
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

                case 5:
                    Number[][] determinantMatrix = readMatrix("");
                    double determinant = calculateDeterminant(determinantMatrix);
                    System.out.println("The result is:");
                    System.out.println(determinant);
                    break;

                case 6:
                    Number[][] matrixForInverse = readMatrix("");
                    Number[][] inverse = inverseMatrix(matrixForInverse);
                    if (inverse != null) {
                        System.out.println("The result is:");
                        printRoundedMatrix(inverse);
                    }
                    break;



            }
        } while (choice != 0);
    }
}
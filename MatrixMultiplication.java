import java.util.Scanner;

public class MatrixMultiplication {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows and columns for the matrices:");
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        int[][] matrixA = new int[rows][cols];
        int[][] matrixB = new int[rows][cols];
        int[][] result = new int[rows][cols];

        // Input for Matrix A
        System.out.println("Enter values for Matrix A:");
        inputMatrix(matrixA, sc);

        // Input for Matrix B
        System.out.println("Enter values for Matrix B:");
        inputMatrix(matrixB, sc);

        // Matrix A
        System.out.println("Matrix A:");
        printMatrix(matrixA);

        // Matrix B
        System.out.println("Matrix B:");
        printMatrix(matrixB);

        // Matrix Multiplication
        multiplyMatrices(matrixA, matrixB, result);

        // Result Matrix
        System.out.println("Result Matrix:");
        printMatrix(result);

        // Closing Scanner
        sc.close();
    }

    public static void inputMatrix(int[][] matrix, Scanner sc) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println("Enter values for row " + (i + 1) + ":");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.println("Enter value for column " + (j + 1) + ":");
                matrix[i][j] = sc.nextInt();
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void multiplyMatrices(int[][] A, int[][] B, int[][] result) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }
}

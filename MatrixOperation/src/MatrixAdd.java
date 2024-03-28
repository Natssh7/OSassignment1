// Some things to change

import java.util.Scanner;
import java.util.InputMismatchException;

public class MatrixAdd {
    private int rows;
    private int cols;
    private int[][] data;

    public MatrixAdd(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }

    public void set(int row, int col, int value) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            data[row][col] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }
    }

    public int get(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return data[row][col];
        } else {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }
    }

    public static MatrixAdd add(MatrixAdd a, MatrixAdd b) {
        if (a.rows != b.rows || a.cols != b.cols) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }

        MatrixAdd result = new MatrixAdd(a.rows, a.cols);
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                result.set(i, j, a.get(i, j) + b.get(i, j));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows for the first matrix:");
        int row1 = scanner.nextInt();

        System.out.println("Enter the number of columns for the first matrix:");
        int col1 = scanner.nextInt();

        System.out.println("Enter the number of rows for the second matrix:");
        int row2 = scanner.nextInt();

        System.out.println("Enter the number of columns for the second matrix:");
        int col2 = scanner.nextInt();

        if (row1 != row2 || col1 != col2) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }

        MatrixAdd matrix1 = new MatrixAdd(row1, col1);
        MatrixAdd matrix2 = new MatrixAdd(row2, col2);

        System.out.println("Enter the elements of the first matrix:");
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                matrix1.set(i, j, scanner.nextInt());
            }
        }

        System.out.println("Enter the elements of the second matrix:");
        for (int i = 0; i < row2; i++) {
            for (int j = 0; j < col2; j++) {
                matrix2.set(i, j, scanner.nextInt());
            }
        }

        MatrixAdd result = MatrixAdd.add(matrix1, matrix2);

        System.out.println("The sum of the matrices is:");
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                System.out.print(result.get(i, j) + " ");
            }
            System.out.println();
        }
    }
}
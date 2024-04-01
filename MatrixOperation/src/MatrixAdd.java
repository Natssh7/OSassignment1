// import java.util.Scanner;

import java.util.Scanner;

public class MatrixAdd {
    private Matrix matrix;

    public MatrixAdd(int rows, int cols) {
        this.matrix = new Matrix(rows, cols);
    }

    public int get(int row, int col) {
        return this.matrix.getValues(row, col);
    }

    // USED FOR SINGLE THREAD

    /* public static MatrixAdd add(MatrixAdd a, MatrixAdd b) {
        if (a.matrix.getLines() != b.matrix.getLines() || a.matrix.getColumn() != b.matrix.getColumn()) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }

        MatrixAdd result = new MatrixAdd(a.matrix.getLines(), a.matrix.getColumn());
        for (int i = 0; i < a.matrix.getLines(); i++) {
            for (int j = 0; j < a.matrix.getColumn(); j++) {
                int sum = a.get(i, j) + b.get(i, j);
                result.matrix.setValues(i, j, sum);
            }
        }
        return result;
    } */

    ////////////////////////////

    public static Matrix add(Matrix a, Matrix b, int numThreads) throws InterruptedException {
        // Checking if the matrix have the same dimensions
        if (a.getLines() != b.getLines() || a.getColumn() != b.getColumn()) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }

        Thread[] threads = new Thread[numThreads];
        Matrix[] partialResults = new Matrix[numThreads];

        int blockSize = a.getLines() / numThreads;
        for (int i = 0; i < numThreads; i++) {
            final int startRow = i * blockSize;
            final int endRow = (i == numThreads - 1) ? a.getLines() : startRow + blockSize;
            final int index = i;
            threads[i] = new Thread(() -> {
                Matrix result = new Matrix(endRow - startRow, a.getColumn());
                for (int row = startRow; row < endRow; row++) {
                    for (int col = 0; col < a.getColumn(); col++) {
                        int sum = a.getValues(row, col) + b.getValues(row, col);
                        result.setValues(row - startRow, col, sum);
                    }
                }
                partialResults[index] = result;
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        Matrix result = new Matrix(a.getLines(), a.getColumn());
        for (int part = 0; part < numThreads; part++) {
            Matrix blockResult = partialResults[part];
            int startRow = part * blockSize;
            for (int i = 0; i < blockResult.getLines(); i++) {
                for (int j = 0; j < blockResult.getColumn(); j++) {
                    result.setValues(i + startRow, j, blockResult.getValues(i, j));
                }
            }
        }

        return result;
    }

    // USED FOR SINGLE THREAD

    public static void main(String[] args) { 
        Scanner scannerAdd = new Scanner(System.in);

        System.out.println("Enter the number of rows for the matrices:");
        try {
            int rows = scannerAdd.nextInt();

            System.out.println("Enter the number of columns for the matrices:");
            int cols = scannerAdd.nextInt();

            Matrix matrix1 = new Matrix(rows, cols);
            Matrix matrix2 = new Matrix(rows, cols);

            long startTimeAdd = System.currentTimeMillis();
            Matrix resultAdd = MatrixAdd.add(matrix1, matrix2, 4);  // Use 4 threads
            long endTimeAdd = System.currentTimeMillis();

            long durationAdd = endTimeAdd - startTimeAdd;
            System.out.println("Execution time for addition with 4 threads in milliseconds: " + durationAdd);
            scannerAdd.close();
        } catch (InterruptedException e) {
            // Handle InterruptedException
            e.printStackTrace();
        } 
    }

    ////////////////////////////
}
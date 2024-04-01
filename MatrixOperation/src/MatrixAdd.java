// import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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

    public static Matrix add(Matrix a, Matrix b, int numThreads) throws InterruptedException, ExecutionException {
        if (a.getLines() != b.getLines() || a.getColumn() != b.getColumn()) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Matrix>> futures = new ArrayList<>();

        int blockSize = a.getLines() / numThreads;
        for (int i = 0; i < numThreads; i++) {
            int startRow = i * blockSize;
            int endRow = (i == numThreads - 1) ? a.getLines() : startRow + blockSize;
            MatrixAdditionTask task = new MatrixAdditionTask(a, b, startRow, endRow);
            Future<Matrix> future = executor.submit(task);
            futures.add(future);
        }

        Matrix result = new Matrix(a.getLines(), a.getColumn());
        for (Future<Matrix> future : futures) {
            Matrix blockResult = future.get();
            for (int i = 0; i < blockResult.getLines(); i++) {
                for (int j = 0; j < blockResult.getColumn(); j++) {
                    result.setValues(i, j, blockResult.getValues(i, j));
                }
            }
        }

        executor.shutdown();

        return result;
    }

    // USED FOR SINGLE THREAD

    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows for the matrices:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of columns for the matrices:");
        int cols = scanner.nextInt();

        MatrixAdd matrix1 = new MatrixAdd(rows, cols);
        MatrixAdd matrix2 = new MatrixAdd(rows, cols);

        System.out.println("The first matrix is:");
        System.out.println(matrix1.matrix.toString());

        System.out.println("The second matrix is:");
        System.out.println(matrix2.matrix.toString());

        // MatrixAdd result = MatrixAdd.add(matrix1, matrix2);
        Matrix result = MatrixAdd.add(matrix1.matrix, matrix2.matrix, 4);

        System.out.println("The sum of the matrices is:");
        System.out.println(result.matrix.toString());

        scanner.close();
    } */

    ////////////////////////////
}
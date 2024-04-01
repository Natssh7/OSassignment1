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

    public static MatrixAdd add(MatrixAdd matrix1, MatrixAdd matrix2) {
        if (matrix1.matrix.getLines() != matrix2.matrix.getLines() || matrix1.matrix.getColumn() != matrix2.matrix.getColumn()) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }

        MatrixAdd result = new MatrixAdd(matrix1.matrix.getLines(), matrix1.matrix.getColumn());
        for (int i = 0; i < matrix1.matrix.getLines(); i++) {
            for (int j = 0; j < matrix1.matrix.getColumn(); j++) {
                int sum = matrix1.matrix.getValues(i, j) + matrix2.matrix.getValues(i, j);
                result.matrix.setValues(i, j, sum);
            }
        }
        return result;
    }

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
        try {
            Scanner scannerAdd = new Scanner(System.in);

            System.out.println("Enter the number of rows for the matrices:");
            int rows = scannerAdd.nextInt();

            System.out.println("Enter the number of columns for the matrices:");
            int cols = scannerAdd.nextInt();

            long sumSingleAdd = 0; // Initialize sumSingleAdd
            long sumMultiAdd = 0; // Initialize sumMultiAdd
            long sum10MultiAdd = 0; // Initialize sum10MultiAdd
            scannerAdd.close(); 
            for(int i=0; i < 100; i++) {
                Matrix matrix1 = new Matrix(rows, cols);
                Matrix matrix2 = new Matrix(rows, cols);

                //System.out.println(matrix1.toString());
                //System.out.println(matrix2.toString());

                long startTimeMultiAdd = System.currentTimeMillis();
                MatrixAdd.add(matrix1, matrix2, 4);  // Use 4 threads
                long endTimeMultiAdd = System.currentTimeMillis();

                long startTime10MultiAdd = System.currentTimeMillis();
                MatrixAdd.add(matrix1, matrix2, 10);  // Use 10 threads
                long endTime10MultiAdd = System.currentTimeMillis();

                long startTimeSingleAdd = System.currentTimeMillis();
                // Assuming matrix1 and matrix2 are initialized and filled elsewhere
                MatrixAdd matrixAdd1 = new MatrixAdd(rows, cols);
                MatrixAdd matrixAdd2 = new MatrixAdd(rows, cols);
                // Now, use matrixAdd1 and matrixAdd2 with addSingle
                MatrixAdd.add(matrixAdd1, matrixAdd2);
                long endTimeSingleAdd = System.currentTimeMillis();

                //System.out.println(MatrixAdd.add(matrix1, matrix2, 4).toString());

                long durationMultiAdd = endTimeMultiAdd - startTimeMultiAdd;
                System.out.println("\nExecution time for addition with 4 threads in milliseconds: " + durationMultiAdd);

                sumMultiAdd += durationMultiAdd; 

                long duration10MultiAdd = endTime10MultiAdd - startTime10MultiAdd;
                System.out.println("Execution time for addition with 10 threads in milliseconds: " + duration10MultiAdd);

                sum10MultiAdd += duration10MultiAdd;

                long durationSingleAdd = endTimeSingleAdd - startTimeSingleAdd;
                System.out.println("Execution time for addition in single thread in milliseconds: " + durationSingleAdd); 
            
                sumSingleAdd += durationSingleAdd;
            }
            System.out.println("\nAverage execution time for addition with 4 threads in milliseconds: " + sumMultiAdd/100);
            System.out.println("Average execution time for addition with 10 threads in milliseconds: " + sum10MultiAdd/100);
            System.out.println("Average execution time for addition in single thread in milliseconds: " + sumSingleAdd/100);
        } catch (InterruptedException e) {
                // Handle InterruptedException
                e.printStackTrace();
        } 
    } 
}


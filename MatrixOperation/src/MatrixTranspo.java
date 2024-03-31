public class MatrixTranspo extends Matrix {

    public MatrixTranspo(Matrix matrix) {
        super(matrix.getColumn(), matrix.getLines(), 0);
        for (int i = 0; i < matrix.getLines(); i++) {
            for (int j = 0; j < matrix.getColumn(); j++) {
                this.setValues(j, i, matrix.getValues(i, j));
            }
        }
    }

    public MatrixTranspo(Matrix matrix, Boolean threadHere) {
        super(matrix.getColumn(), matrix.getLines(), 0);
        int numThreads = Math.min(Runtime.getRuntime().availableProcessors(), matrix.getLines());
        Thread[] threads = new Thread[numThreads];

        for (int t = 0; t < numThreads; t++) {
            final int start = t * matrix.getLines() / numThreads;
            final int end = (t + 1) * matrix.getLines() / numThreads;

            threads[t] = new Thread(() -> {
                for (int row = start; row < end; row++) {
                    for (int col = 0; col < matrix.getColumn(); col++) {
                        setValues(col, row, matrix.getValues(row, col));
                    }
                }
            });
            threads[t].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("error");
            }
        }
    }

}
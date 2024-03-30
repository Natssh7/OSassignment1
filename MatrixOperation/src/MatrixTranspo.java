public class MatrixTranspo extends Matrix {

    public MatrixTranspo(Matrix matrix) {
        super(4, 4, 0);
        System.out.println(this.toString());
        for (int i = 0; i < matrix.getLines(); i++) {
            for (int j = 0; j < matrix.getColumn(); j++) {
                this.setValues(j, i, matrix.getValues(i, j));
            }
        }
    }

    public MatrixTranspo(Matrix matrix, Boolean threadHere) {
        super(4, 4, 0);
        System.out.println("icic");
        int numThreads = Runtime.getRuntime().availableProcessors(); // number of disponible thread
        int segment;
        if (numThreads > matrix.getColumn()) {
            segment = 1;
        } else {
            segment = matrix.getColumn() / numThreads;
        }
        System.out.println(segment);
        System.out.println(numThreads);

        Thread[] thread = new Thread[matrix.getColumn() / segment];
        for (int i = 0; i < matrix.getColumn() / segment; i++) {
            final int row = i;
            Runnable task = () -> transposeRow(row, matrix);
            thread[i] = new Thread(task);
            thread[i].start();
        }

        for (Thread threads : thread) {
            try {
                threads.join();
            } catch (InterruptedException e) {
                System.out.println("erreur");
            }
        }
    }

    public void transposeRow(int row, Matrix matrix) {
        for (int i = 0; i < matrix.getColumn(); i++) {
            this.setValues(i, row, matrix.getValues(row, i));
        }
    }

}
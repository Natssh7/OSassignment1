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

        for (int i = 0; i < matrix.getLines(); i++) {
            for (int j = 0; j < matrix.getColumn(); j++) {
                this.setValues(j, i, matrix.getValues(i, j));
            }
        }
    }

}
import java.util.concurrent.Callable;

public class MatrixAdditionTask implements Callable<Matrix> {
    private final Matrix a;
    private final Matrix b;
    private final int startRow;
    private final int endRow;

    public MatrixAdditionTask(Matrix a, Matrix b, int startRow, int endRow) {
        this.a = a;
        this.b = b;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    public Matrix call() {
        Matrix result = new Matrix(a.getLines(), a.getColumn());
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < a.getColumn(); j++) {
                int sum = a.getValues(i, j) + b.getValues(i, j);
                result.setValues(i, j, sum);
            }
        }
        return result;
    }
}
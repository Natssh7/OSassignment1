import java.util.Scanner;

public class MatrixAdd {
    private Matrix matrix;

    public MatrixAdd(int rows, int cols) {
        this.matrix = new Matrix(rows, cols);
    }

    public int get(int row, int col) {
        return this.matrix.getValues(row, col);
    }

    public static MatrixAdd add(MatrixAdd a, MatrixAdd b) {
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
    }

    public static void main(String[] args) {
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

        MatrixAdd result = MatrixAdd.add(matrix1, matrix2);

        System.out.println("The sum of the matrices is:");
        System.out.println(result.matrix.toString());

        scanner.close();
    }
}
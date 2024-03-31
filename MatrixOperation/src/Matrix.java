import java.util.Random;

public class Matrix {

    private int[][] matrix;

    public Matrix(int n, int m) {
        Random random = new Random();
        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int randomInt = random.nextInt();
                matrix[i][j] = randomInt;
            }
        }
    }

    public Matrix(int n, int m, int val) {
        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = val;
            }
        }
    }

    public void setValues(int n, int m, int values) {
        matrix[n][m] = values;
    }

    public int getLines() {
        return matrix.length;
    }

    public int getColumn() {
        return matrix[0].length;
    }

    public int getValues(int n, int m) {
        return matrix[n][m];
    }

    @Override
    public String toString() {
        String s = "the matrix is \n";
        for (int i = 0; i < this.getLines(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                s = s + matrix[i][j] + "   ";
            }
            s = s + "\n";
        }
        return s;
    }
}
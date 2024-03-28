public class Matrix {

    private int[][] matrix;

    public Matrix(int n, int m) {
        matrix = new int[n][m];
    }

    public void setValues(int n, int m) {
        matrix = new int[n][m];
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

}
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Matrix A = new Matrix(4, 4);
        System.out.print(A.toString());
        System.out.println("After transposition");
        Matrix B = new MatrixTranspo(A);
        System.out.print(B.toString());

    }

}
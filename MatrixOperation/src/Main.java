import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("1 for add, 2 for multiplication and 3 for transposition");
        int exo = scanner.nextInt();

        switch (exo) {
            case 1:
                System.out.println("Enter the number of rows for the first matrix:");
                int row1 = scanner.nextInt();

                System.out.println("Enter the number of columns for the first matrix:");
                int col1 = scanner.nextInt();

                System.out.println("Enter the number of rows for the second matrix:");
                int row2 = scanner.nextInt();

                System.out.println("Enter the number of columns for the second matrix:");
                int col2 = scanner.nextInt();
                break;
            case 2:
                break;
            case 3:
                System.out.println("Do you want a square matrix ? y/n");
                String square = scanner.next();
                switch (square) {
                    case "y":
                        System.out.println("Enter the number of rows for the matrix:");
                        int n = scanner.nextInt();
                        double time = 0;
                        for (int i = 0; i < 100; i++) {
                            Matrix A = new Matrix(n, n);

                            System.out.println("After transposition");

                            long startTimetranspo = System.currentTimeMillis();
                            Matrix C = new MatrixTranspo(A);
                            long endTime = System.currentTimeMillis();
                            long durationWThread = (endTime - startTimetranspo);

                            System.out.println(
                                    "Execution time for transposition without thread in milliseconde : "
                                            + durationWThread);

                            long startTimetranspoThread = System.currentTimeMillis();
                            Matrix B = new MatrixTranspo(A, true);
                            long endTimeThread = System.currentTimeMillis();

                            long duration = (endTimeThread - startTimetranspoThread);
                            time = time + duration;
                        }
                        time = time / 100;
                        System.out.println(
                                "Execution time for transposition with thread in milliseconde for 100 matrix : "
                                        + time);
                        break;
                    case "n":
                        System.out.println("Enter the number of rows for the first matrix:");
                        int row = scanner.nextInt();
                        System.out.println("Enter the number of columns for the first matrix:");
                        int col = scanner.nextInt();
                        Matrix A2 = new Matrix(row, col);
                        System.out.println("After transposition");

                        long startranspo = System.currentTimeMillis();
                        Matrix C2 = new MatrixTranspo(A2, true);
                        long end = System.currentTimeMillis();
                        long durThread = (end - startranspo);

                        System.out.println(
                                "Execution time for transposition with thread in milliseconde : " + durThread);

                        long startWtranspo = System.currentTimeMillis();
                        Matrix B2 = new MatrixTranspo(A2);
                        long endW = System.currentTimeMillis();
                        long durWThread = (endW - startWtranspo);

                        System.out.println(
                                "Execution time for transposition without thread in milliseconde : "
                                        + durWThread);

                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        scanner.close();

    }

}
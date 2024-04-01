import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("1 for add, 2 for multiplication and 3 for transposition");
        int exo = scanner.nextInt();

        switch (exo) {
            case 1:
                MatrixAdd.main(null);
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
                        double timeThread = 0;
                        double timeWhithout = 0;
                        for (int i = 0; i < 1000; i++) {
                            Matrix A = new Matrix(n, n);

                            long startTimetranspo = System.currentTimeMillis();
                            Matrix C = new MatrixTranspo(A);
                            long endTime = System.currentTimeMillis();
                            long durationWThread = (endTime - startTimetranspo);
                            timeWhithout = timeWhithout + durationWThread;

                            long startTimetranspoThread = System.currentTimeMillis();
                            Matrix B = new MatrixTranspo(A, true);
                            long endTimeThread = System.currentTimeMillis();

                            long duration = (endTimeThread - startTimetranspoThread);
                            timeThread = timeThread + duration;
                        }
                        System.out.println("After transposition");
                        timeThread = timeThread / 1000;
                        timeWhithout = timeWhithout / 1000;
                        System.out.println(
                                "Execution time for transposition with thread in milliseconde for 1000 matrix : "
                                        + timeThread);
                        System.out.println(
                                "Execution time for transposition without thread in milliseconde for 1000 matrix : "
                                        + timeWhithout);
                        break;
                    case "n":
                        System.out.println("Enter the number of rows for the first matrix:");
                        int row = scanner.nextInt();
                        System.out.println("Enter the number of columns for the first matrix:");
                        int col = scanner.nextInt();
                        double timeThread2 = 0;
                        double timeWhithout2 = 0;
                        for (int i = 0; i < 1000; i++) {
                            Matrix A2 = new Matrix(row, col);

                            long startranspo = System.currentTimeMillis();
                            Matrix C2 = new MatrixTranspo(A2, true);
                            long end = System.currentTimeMillis();
                            long durThread = (end - startranspo);
                            timeThread2 = timeThread2 + durThread;

                            long startWtranspo = System.currentTimeMillis();
                            Matrix B2 = new MatrixTranspo(A2);
                            long endW = System.currentTimeMillis();
                            long durWThread = (endW - startWtranspo);
                            timeWhithout2 = timeWhithout2 + durWThread;
                        }
                        timeThread2 = timeThread2 / 1000;
                        timeWhithout2 = timeWhithout2 / 1000;
                        System.out.println("After transposition");
                        System.out.println(
                                "Execution time for transposition with thread in milliseconde for 1000 matrix : "
                                        + timeThread2);
                        System.out.println(
                                "Execution time for transposition without thread in milliseconde for 1000 matrix : "
                                        + timeWhithout2);

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
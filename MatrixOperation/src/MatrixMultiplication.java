public class MatrixMultiplication extends Matrix{

    public MatrixMultiplication(Matrix matrixA, Matrix matrixB){
        super(matrixA.getLines(),matrixB.getColumn(),0);
    }
    public static Matrix Multiplication(Matrix matrixA, Matrix matrixB){
        MatrixMultiplication matrixC = new MatrixMultiplication(matrixA,matrixB);
        if(matrixA.getColumn() == matrixB.getLines()){
            for (int i = 0; i < matrixC.getLines(); i++) {
                for (int j = 0; j < matrixC.getColumn(); j++) {
                    int c = 0;
                    for (int k = 0; k < matrixA.getColumn(); k++){
                        c += matrixA.getValues(i, k)*matrixB.getValues(k, j);
                    }
                    matrixC.setValues(i, j, c);
                }
            }
        }
        else{
            System.out.println("Error: impossible operation asked");
        }
        return matrixC;
    }

    public static Matrix MultiplicationWithThread(Matrix matrixA, Matrix matrixB){


    public static void main(String[] args) {
        Matrix A1 = new Matrix(2, 2);
        A1.setValues(0,0,1);
        A1.setValues(0,1,2);
        A1.setValues(1,0,3);
        A1.setValues(1,1,4);
        Matrix A2 = new Matrix(2, 3);
        A2.setValues(0,0,1);
        A2.setValues(0,1,2);
        A2.setValues(0,2,3);
        A2.setValues(1,0,4);
        A2.setValues(1,1,5);
        A2.setValues(1,2,6);
        //System.out.println(A2.toString());
        //System.out.println(A2.getColumn());
        System.out.println(MatrixMultiplication.Multiplication(A1,A2).toString());
        //System.out.println(MatrixMultiplication.Multiplication(A2,A1).toString());
    }

}


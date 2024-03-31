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

}

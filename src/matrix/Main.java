package matrix;

public class Main {
    public static void main(String[] args) throws Exception {
        double[][] arr1 = {{5, 1}, {6, 7}};
        Matrix m1 = new Matrix(arr1);

        double[][] arr2 = {{1, 6}, {0, 1}};
        Matrix m2 = new Matrix(arr2);

        m1.Gaussian();
        
    }
}

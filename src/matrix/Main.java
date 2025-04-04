package matrix;

public class Main {
    public static void main(String[] args) throws Exception {
        double[][] arr1 = {{1, 1}, {2, 5}};
        Matrix m1 = new Matrix(arr1);

        System.out.println(m1.Determinant());

        double[][] arr2 = {{5}};
        Matrix m2 = new Matrix(arr2);

        System.out.println(m2.Determinant());
    }
}

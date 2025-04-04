package test;
import matrix.Matrix;


public class TestMatrix {
    public static boolean test1() throws Exception {
        double[][] arr1 = {{1, 0}, {0, 1}};
        double[][] arr2 = {{0, 2}, {3, 0}};
        Matrix m1 = new Matrix(arr1);
        Matrix m2 = new Matrix(arr2);

        m1.Plus(m2);

        double[][] arr3 = {{1, 2}, {3, 1}};
        Matrix m3 = new Matrix(arr3);

        if (m1.Equals(m3)) return true;
        else return false;
    }   

    public static boolean test2() throws Exception {
        double[][] arr1 = {{1, 1}, {2, 5}};
        Matrix m1 = new Matrix(arr1);

        if (m1.Determinant() == 3) return true;
        else return false;
    }

    public static boolean test3() throws Exception {
        double[][] arr2 = {{5}};
        Matrix m2 = new Matrix(arr2);

        if (m2.Determinant() == 5) return true;
        else return false;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("test1 passed: " + test1());
        System.out.println("test2 passed: " + test2());
        System.out.println("test3 passed: " + test3());
    }
}

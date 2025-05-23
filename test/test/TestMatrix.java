package test;

import matrix.Matrix;

public class TestMatrix {
    public static boolean addition() throws Exception {
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

    public static boolean determinant1() throws Exception {
        double[][] arr1 = {{1, 1}, {2, 5}};
        Matrix m1 = new Matrix(arr1);

        if (m1.Determinant() == 3) return true;
        else return false;
    }

    public static boolean determinant2() throws Exception {
        double[][] arr2 = {{5}};
        Matrix m2 = new Matrix(arr2);

        if (m2.Determinant() == 5) return true;
        else return false;
    }

    public static boolean gaussian1() throws Exception {
        double[][] arr1 = {{5, 10}, {3, 8}};
        Matrix m1 = new Matrix(arr1);

        double[][] arr2 = {{1, 2}, {0, 1}};
        Matrix m2 = new Matrix(arr2);

        m1.Gaussian();
        if (m2.Equals(m1)) return true;
        else return false;
    }

    public static boolean gaussian2() throws Exception {
        double[][] arr1 = {{3, 10}, {1, 10}};
        Matrix m1 = new Matrix(arr1);

        double[][] arr2 = {{1, 3.33333}, {0, 1}};
        Matrix m2 = new Matrix(arr2);

        m1.Gaussian();
        if (m2.Equals(m1)) return true;
        else return false;
    }

    public static boolean multiplication() throws Exception {
        double[][] arr1 = {{5, 10}, {3, 8}};
        Matrix m1 = new Matrix(arr1);

        double[][] arr2 = {{1, 2}, {0, 1}};
        Matrix m2 = new Matrix(arr2);

        m1.Times(m2);

        double[][] arr3 = {{5, 20}, {3, 14}};
        Matrix m3 = new Matrix(arr3);

        if (m1.Equals(m3)) return true;
        else return false;
    }
    
    public static boolean transpose() throws Exception {
        double[][] arr1 = {{5, 10, 9}, {3, 8, 99}};
        Matrix m1 = new Matrix(arr1);

        m1.Transpose();

        double[][] arr3 = {{5, 3}, {10, 8}, {9, 99}};
        Matrix m3 = new Matrix(arr3);

        if (m1.Equals(m3)) return true;
        else return false;
    }

    public static boolean copy() throws Exception {
        double[][] arr1 = {{5, 10, 9}, {3, 8, 99}};
        Matrix m1 = new Matrix(arr1);

        double[][] arr2 = m1.getData();
        Matrix m2 = new Matrix(arr2);
        
        if (m1.Equals(m2)) return true;
        else return false;
    }
    
    public static void main(String[] args) throws Exception {
        // System.out.println("test1 passed: " + test1());
        // System.out.println("test2 passed: " + test2());
        // System.out.println("test3 passed: " + test3());
        // System.out.println("test4 passed: " + test4());
        // System.out.println("test5 passed: " + test5());

        boolean allTestsPassed = true;

        if (!addition()) {
            System.out.println("addition failed: Matrix addition result is incorrect.");
            allTestsPassed = false;
        } else {
            System.out.println("addition passed");
        }

        if (!determinant1()) {
            System.out.println("determinant1 failed: Determinant is incorrect.");
            allTestsPassed = false;
        } else {
            System.out.println("determinant1 passed");
        }

        if (!determinant2()) {
            System.out.println("determinant2 failed: Determinant is incorrect.");
            allTestsPassed = false;
        } else {
            System.out.println("determinant2 passed");
        }

        if (!gaussian1()) {
            System.out.println("gaussian1 failed: Gaussian elimination result is incorrect.");
            allTestsPassed = false;
        } else {
            System.out.println("gaussian1 passed");
        }

        if (!gaussian2()) {
            System.out.println("gaussian2 failed: Gaussian elimination result is incorrect.");
            allTestsPassed = false;
        } else {
            System.out.println("gaussian2 passed");
        }

        if (!multiplication()) {
            System.out.println("multiplication failed: Multiplication result is incorrect.");
            allTestsPassed = false;
        } else {
            System.out.println("multiplication passed");
        }

        if (!transpose()) {
            System.out.println("transpose failed: Transpose result is incorrect.");
            allTestsPassed = false;
        } else {
            System.out.println("transpose passed");
        }

        if (!copy()) {
            System.out.println("copy failed: copy result is incorrect.");
            allTestsPassed = false;
        } else {
            System.out.println("copy passed");
        }

        if (!allTestsPassed) {
            System.exit(1);
        }
    }
}

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

    public static boolean test4() throws Exception {
        double[][] arr1 = {{5, 10}, {3, 8}};
        Matrix m1 = new Matrix(arr1);

        double[][] arr2 = {{1, 2}, {0, 1}};
        Matrix m2 = new Matrix(arr2);

        m1.Gaussian();
        if (m2.Equals(m1)) return true;
        else return false;
    }

    public static boolean test5() throws Exception {
        double[][] arr1 = {{3, 10}, {1, 10}};
        Matrix m1 = new Matrix(arr1);

        double[][] arr2 = {{1, 3.33333}, {0, 0}}; // 0 1
        Matrix m2 = new Matrix(arr2);

        m1.Gaussian();
        if (m2.Equals(m1)) return true;
        else return false;
    }

    public static void main(String[] args) throws Exception {
        // System.out.println("test1 passed: " + test1());
        // System.out.println("test2 passed: " + test2());
        // System.out.println("test3 passed: " + test3());
        // System.out.println("test4 passed: " + test4());
        // System.out.println("test5 passed: " + test5());

        boolean allTestsPassed = true;

        if (!test1()) {
            System.out.println("test1 failed: Matrix addition result is incorrect.");
            allTestsPassed = false;
        } else {
            System.out.println("test1 passed");
        }

        if (!test2()) {
            System.out.println("test2 failed: Determinant is incorrect.");
            allTestsPassed = false;
        } else {
            System.out.println("test2 passed");
        }

        if (!test3()) {
            System.out.println("test3 failed: Determinant is incorrect.");
            allTestsPassed = false;
        } else {
            System.out.println("test3 passed");
        }

        if (!test4()) {
            System.out.println("test4 failed: Gaussian elimination result is incorrect.");
            allTestsPassed = false;
        } else {
            System.out.println("test4 passed");
        }

        if (!test5()) {
            System.out.println("test5 failed: Gaussian elimination result is incorrect.");
            allTestsPassed = false;
        } else {
            System.out.println("test5 passed");
        }

        if (!allTestsPassed) {
            System.exit(1);
        }
    }
}

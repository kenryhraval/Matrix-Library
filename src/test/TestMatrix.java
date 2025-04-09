import matrix.Matrix;

public class TestMatrix {

    @Test
    public void test1() throws Exception {
        double[][] arr1 = {{1, 0}, {0, 1}};
        double[][] arr2 = {{0, 2}, {3, 0}};
        Matrix m1 = new Matrix(arr1);
        Matrix m2 = new Matrix(arr2);

        m1.Plus(m2);

        double[][] arr3 = {{1, 2}, {3, 1}};
        Matrix m3 = new Matrix(arr3);

        assertTrue(m1.Equals(m3));
    }

    @Test
    public void test2() throws Exception {
        double[][] arr1 = {{1, 1}, {2, 5}};
        Matrix m1 = new Matrix(arr1);

        assertEquals(3, m1.Determinant());
    }

    @Test
    public void test3() throws Exception {
        double[][] arr2 = {{5}};
        Matrix m2 = new Matrix(arr2);

        assertEquals(5, m2.Determinant());
    }

    @Test
    public void test4() throws Exception {
        double[][] arr1 = {{5, 10}, {3, 8}};
        Matrix m1 = new Matrix(arr1);

        double[][] arr2 = {{1, 2}, {0, 1}};
        Matrix m2 = new Matrix(arr2);

        m1.Gaussian();
        assertTrue(m2.Equals(m1));
    }

    @Test
    public void test5() throws Exception {
        double[][] arr1 = {{3, 10}, {1, 10}};
        Matrix m1 = new Matrix(arr1);

        double[][] arr2 = {{1, 3.33333}, {0, 1}};
        Matrix m2 = new Matrix(arr2);

        m1.Gaussian();
        assertTrue(m2.Equals(m1));
    }
}

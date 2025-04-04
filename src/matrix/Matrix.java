package matrix;

public class Matrix {
    protected double[][] matrix;
    protected int width;
    protected int height;
    
    public Matrix(double[][] matrix) {

        if (matrix.length == 0 ) {
            throw new IllegalArgumentException("Matrix must have at least one row.");
        } 
        if (matrix[0].length == 0 ) {
            throw new IllegalArgumentException("Matrix must have at least one column.");
        } 

        this.matrix = matrix;
        this.Update();
    }

    public boolean Equals(Matrix matrix) {
        if (matrix.width != this.width) return false;
        if (matrix.height != this.height) return false;
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (this.matrix[i][j] != matrix.matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void Update() {
        this.height = matrix.length;
        this.width = matrix[0].length;
    }

    public void Plus(Matrix m2) throws Exception {
        if (this.width != m2.width) {
            throw new Exception("Matrices must have the same number of columns.");
        } 
        if (this.height != m2.height) {
            throw new Exception("Matrices must have the same number of rows.");
        } 
        
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.matrix[i][j] += m2.matrix[i][j];
            }
        }
    }

    public void Times(Matrix m2) throws Exception {
        if (this.width != m2.height) {
            throw new Exception("Dimensions must match.");
        } 

        double[][] updated = new double[this.height][];
        for (int i = 0; i < this.height; i++) {
            updated[i] = new double[m2.width];
        }

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                updated[i][j] = 0;

                for (int k = 0; k < this.width; k++) {
                    updated[i][j] += this.matrix[i][k] * m2.matrix[k][j];
                } 
            }
        }

        this.matrix = updated;
        this.Update();
        
    }

    public double Determinant() throws Exception {
        if (this.width != this.height) {
            throw new Exception("Matrix must be square.");
        }

        return Subtask(this.matrix, this.width);
    }

    private double Subtask(double[][] matrix, int N) {
        if (N==1) return matrix[0][0];

        double result = 0;

        int i = 0; // implement Laplace's expansion
        for (int j = 0; j < N; j++) {

            // initialise the minor
            double[][] m = new double[N-1][];
            for (int k = 0; k < N-1; k++) {
                m[k] = new double[N-1];
            }

            for (int i1 = 0, i2 = 0; i1 < N-1; i1++, i2++) {
                for (int j1 = 0, j2 = 0; j1 < N-1; j1++, j2++) {
                    if (j2 == j) j2++;
                    if (i2 == i) i2++;  
                    m[i1][j1] = matrix[i2][j2];
                }
            }

            int parity = ((i+j) % 2 == 0) ? 1 : -1;
            result += parity * matrix[i][j] * Subtask(m, N-1);
        }

        return result;
        
    }

    public void Gaussian() {
        // find rows of zeros and move them down
        
        // divide and subtract
    }
}
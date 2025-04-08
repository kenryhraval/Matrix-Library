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
                if (Math.abs(this.matrix[i][j] - matrix.matrix[i][j]) > 0.00001) {
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

        return Determinat_Subtask(this.matrix, this.width);
    }

    private double Determinat_Subtask(double[][] matrix, int N) {
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
            result += parity * matrix[i][j] * Determinat_Subtask(m, N-1);
        }

        return result;
        
    }

    public void Gaussian() {
        // the problem is that not only rows of 'pure' zeros should be moved
        // but also the ones with zeros in bad positions, like:
        // 0 1 0
        // 1 0 0
        // 0 1 1
        Gaussian_Subtask(0, 0);
    }

    private void Gaussian_Subtask(int m, int n) {
        // for debug
        // for (int i = 0; i < this.height; i++) {
        //     for (int j = 0; j < this.width; j++) {
        //         System.out.print(this.matrix[i][j] + " ");
        //     }
        //     System.out.println();
        // } System.out.println();

        if (m > this.height-1 || n > this.width-1) return;

        if (this.matrix[m][n] == 0) Gaussian_Tidy(m, n);
        // a free variable detected (no pivot)
        if (this.matrix[m][n] == 0) Gaussian_Subtask(m, n+1);
        
        else {
            // to get a one on the pivot, divide by it
            for (int j = n+1; j < this.width; j++) {
                this.matrix[m][j] /= this.matrix[m][n]; 
            }

            // change the pivot at last
            this.matrix[m][n] = 1;

            // zeros down the column m
            for (int i = m+1; i < this.height; i++) {

                // to get a zero under, all elements of the row are affected
                for (int j = n+1; j < this.width; j++) {
                    this.matrix[i][j] -= this.matrix[m][j] * this.matrix[i][n];
                }

                // change to zero at last
                this.matrix[i][n] = 0;
            }

            // next rectangle
            Gaussian_Subtask(m+1, n+1);
        }
    }

    private void Gaussian_Tidy(int m, int n) {
        for (int i = this.height-1; i > m; i--) {
            if (this.matrix[i][n] != 0) {
                // copy the last non-zero row
                double[] row1 = Copy_Row(i);
                double[] row2 = Copy_Row(m);
                Paste_Row(i, row2);
                Paste_Row(m, row1);
            }
        }
    }

    private double[] Copy_Row(int i) {
        double[] row = new double[this.width];
        for (int j = 0; j < this.width; j++) {
            row[j] = this.matrix[i][j];
        }
        return row;
    }

    private void Paste_Row(int i, double[] row) {
        for (int j = 0; j < this.width; j++) {
            this.matrix[i][j] = row[j];
        }
    }
}
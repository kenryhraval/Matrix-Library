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

        int result = 0;

        for (int i = 0; i < N; i++) {
            
        }

        return result;
        
    }


}
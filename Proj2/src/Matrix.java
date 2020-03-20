public class Matrix {
    int[][] matrix;
    int x, y, columns;

    public Matrix(int[][] data) {
        this(data, 0, 0, data.length);
    }

    private Matrix(int[][] data, int x, int y, int columns) {
        this.matrix = data;
        this.x = x;
        this.y = y;
        this.columns = columns;
    }

    public Matrix getSubMatrix(int x, int y, int columns) {
        return new Matrix(matrix, this.x + x , this.y + y, columns);
    }

    public int getValue(int row, int col) {
        return matrix[x + row][y + col];
    }
}
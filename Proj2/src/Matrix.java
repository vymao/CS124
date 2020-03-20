public class Matrix {
    int[][] matrix;
    int x, y, columns, rows;

    public Matrix(int[][] data) {
        this(data, 0, 0, data.length, data[0].length);
    }

    private Matrix(int[][] data, int x, int y, int columns, int rows) {
        this.matrix = data;
        this.x = x;
        this.y = y;
        this.columns = columns;
        this.rows = rows;
    }

    public Matrix getSubMatrix(int x, int y, int columns, int rows) {
        return new Matrix(matrix, this.x + x , this.y + y, columns, rows);
    }

    public int getValue(int row, int col) {
        return matrix[x + row][y + col];
    }
}
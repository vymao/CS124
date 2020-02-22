import java.util.*;

public class Graph {

    /*
    public static class SparseMatrix<T> {
        private T defaultValue;
        private int m;
        private int n;
        public Map<Integer, T> data = new TreeMap<Integer, T>();
        /// create a new matrix with m rows and n columns
        public SparseMatrix(int m, int n, T defaultValue) {
            this.m = m;
            this.n = n;
            this.defaultValue = defaultValue;
        }
        /// set value at [i,j] (row, col)


        public void setValueAt(int i, int j, T value) {
            if (i > m || j > n || i < 0 || j < 0)
                throw new IllegalArgumentException(
                        "index (" + i + ", " +j +") out of bounds");
            data.put(i * n + j, value);
        }
        /// retrieve value at [i,j] (row, col)
        public T getValueAt(int i, int j) {
            if (i > m || j > n || i < 0 || j < 0)
                throw new IllegalArgumentException(
                        "index (" + i + ", " +j +") out of bounds");
            T value = data.get(i * n + j);
            return value != null ? value : defaultValue;
        }

    }
     */
    public float[][] listNodes;
    //private double[][] adj_matrix;

    public Graph(int num_vertices, int dimension) {
        listNodes = new float[num_vertices][dimension];

        for (int i = 0; i < num_vertices; i++) {
            for (int j = 0; j < dimension; j++) {
                listNodes[i][j] = GraphGenerator.generateRandomCoordinate();
            }
        }
        /*
        adj_matrix = new SparseMatrix<Float>(num_vertices, num_vertices, 2.0f);
        for (int i = 1; i <= num_vertices; i++) {
            for (int j = i; j <= num_vertices; j++) {
                adj_matrix.setValueAt(i, j, 2.0f);
            }
        }

         */

    }

    public float[] getCoordinate(int node) {
        return listNodes[node];
    }

    /*
    public float getEdge(int start_vertex, int end_vertex){
        return adj_matrix.getValueAt(start_vertex, end_vertex);
        //return adj_matrix[start_vertex - 1][end_vertex - 1];
    }

    public void setEdge(int start_vertex, int end_vertex, float value) {
        adj_matrix.setValueAt(start_vertex, end_vertex, value);
        //adj_matrix[start_vertex - 1][end_vertex - 1] = value;
    }

     */


}

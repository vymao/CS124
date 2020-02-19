public class Graph {
    private double[][] adj_matrix;

    public Graph(int num_vertices) {
        adj_matrix = new double[num_vertices][num_vertices];
        for (int i = 0; i < num_vertices; i++) {
            for (int j = i; j < num_vertices; j++) {
                if (i == j) {
                    adj_matrix[i][j] = 0;
                } else {
                    adj_matrix[i][j] = -1.0;
                }
            }
        }
    }

    public double getEdge(int start_vertex, int end_vertex){
        return adj_matrix[start_vertex][end_vertex];
    }

    public void setEdge(int start_vertex, int end_vertex, double value) {
        adj_matrix[start_vertex][end_vertex] = value;
    }
}

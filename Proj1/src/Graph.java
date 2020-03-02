import java.util.*;

public class Graph {
    private float[][] listNodes;

    public Graph(int num_vertices, int dimension) {
        listNodes = new float[num_vertices][dimension];

        for (int i = 0; i < num_vertices; i++) {
            for (int j = 0; j < dimension; j++) {
                listNodes[i][j] = GraphGenerator.generateRandomCoordinate();
            }
        }


    }

    public float[] getCoordinate(int node) {
        return listNodes[node];
    }
}

import java.util.*;

public class TriangleGraph {
    final static int dim = 1024;

    public static int countTriangles(float prob) {
        int[][] graph = new int[dim][dim];
        Random generator = new Random();

        for (int i = 0; i < dim; i++) {
            for (int j = i + 1; j < dim; j++) {
                float nextint = generator.nextFloat();
                if (nextint < prob) {
                    graph[i][j] = 1;
                    graph[j][i] = 1;
                }
            }
        }



        int[][] graph2 = Solver.runStrassen(graph, graph, 15);
        int[][] graph3 = Solver.runStrassen(graph2, graph, 15);

        float sum = 0;
        for (int i = 0; i < dim; i++) {
            sum += graph3[i][i];
        }

        sum = sum / 6;
        //System.out.println("Triangles: " + Float.toString(sum));
        return (int) sum;
    }
}

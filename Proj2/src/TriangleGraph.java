import java.util.*;
import java.util.concurrent.TransferQueue;

public class TriangleGraph {
    final static int dim = 1024;

    public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

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

        //print2D(graph);



        int[][] graph2 = Solver.runStrassen(graph, graph, 15);
        int[][] graph3 = Solver.runStrassen(graph2, graph, 15);

        float sum = 0;
        for (int i = 0; i < dim; i++) {
            sum += graph3[i][i];
        }

        sum = sum / 6;
        System.out.println("Triangles: " + Float.toString(sum));
        return (int) sum;
    }


}

import java.util.*;
import java.lang.Math;

public class GraphGenerator {
    /*
     Complete graphs on n vertices, where the weight of each edge is a real number chosen uniformly at
random on [0, 1].
• Complete graphs on n vertices, where the vertices are points chosen uniformly at random inside the
unit square. (That is, the points are (x, y), with x and y each a real number chosen uniformly at
random from [0, 1].) The weight of an edge is just the Euclidean distance between its endpoints.
• Complete graphs on n vertices, where the vertices are points chosen uniformly at random inside the
unit cube (3 dimensions) and hypercube (4 dimensions). As with the unit square case above, the
weight of an edge is just the Euclidean distance between its endpoints.
     */
    private static Random generator;

    public static float euclideanDistance(float[] start, float[] end) {
        float sum = 0.0f;
        for (int i = 0; i < start.length; i++) {
            sum = sum + (float) Math.pow(start[i] - end[i], 2);
        }

        return (float) Math.sqrt(sum);
    }

    public static Graph generate1DGraph(int vertices) {
        int seed = 100;
        generator = new Random(seed);
        float n;
        Graph g = new Graph(vertices);

        for (int start_vertex = 1; start_vertex <= vertices; start_vertex++) {
            for (int end_vertex = start_vertex; end_vertex <= vertices; end_vertex++) {
                if (start_vertex != end_vertex) {
                    n = generator.nextFloat();
                    g.setEdge(start_vertex, end_vertex, n);
                }
            }
        }

        return g;
    }

    public static Graph generate2DGraph(int vertices) {
        int seed = 200;
        generator = new Random(seed);
        float start, end;
        Graph g = new Graph(vertices);

        for (int start_vertex = 1; start_vertex <= vertices; start_vertex++) {
            for (int end_vertex = start_vertex; end_vertex <= vertices; end_vertex++) {
                if (start_vertex != end_vertex) {
                    float distance = euclideanDistance(
                            new float[] {generator.nextFloat(), generator.nextFloat()},
                            new float[] {generator.nextFloat(), generator.nextFloat()});
                    g.setEdge(start_vertex, end_vertex, distance);
                }
            }
        }

        return g;
    }

    public static Graph generate3DGraph(int vertices) {
        int seed = 200;
        generator = new Random(seed);
        double start, end;
        Graph g = new Graph(vertices);

        for (int start_vertex = 1; start_vertex <= vertices; start_vertex++) {
            for (int end_vertex = start_vertex; end_vertex <= vertices; end_vertex++) {
                if (start_vertex != end_vertex) {
                    float distance = euclideanDistance(
                            new float[] {generator.nextFloat(), generator.nextFloat(), generator.nextFloat()},
                            new float[] {generator.nextFloat(), generator.nextFloat(), generator.nextFloat()});
                    g.setEdge(start_vertex, end_vertex, distance);
                }
            }
        }

        return g;
    }

    public static Graph generate4DGraph(int vertices) {
        int seed = 200;
        generator = new Random(seed);
        float start, end;
        Graph g = new Graph(vertices);

        for (int start_vertex = 1; start_vertex <= vertices; start_vertex++) {
            for (int end_vertex = start_vertex; end_vertex <= vertices; end_vertex++) {
                if (start_vertex != end_vertex) {
                    float distance = euclideanDistance(
                            new float[] {generator.nextFloat(), generator.nextFloat(), generator.nextFloat(), generator.nextFloat()},
                            new float[] {generator.nextFloat(), generator.nextFloat(), generator.nextFloat(), generator.nextFloat()});
                    g.setEdge(start_vertex, end_vertex, distance);
                }
            }
        }

        return g;
    }

}

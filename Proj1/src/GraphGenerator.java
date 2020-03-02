import java.util.*;
import java.lang.Math;

public class GraphGenerator {

    public static float generateRandomCoordinate() {
        Random generator = new Random();
        return generator.nextFloat();
    }

    public static float euclideanDistance(float[] start, float[] end) {
        float sum = 0.0f;
        for (int i = 0; i < start.length; i++) {
            sum = sum + (float) Math.pow(start[i] - end[i], 2);
        }

        return (float) Math.sqrt(sum);
    }

    public static Graph generate1DGraph(int vertices) {
        return new Graph(vertices, 1);
    }

    public static Graph generate2DGraph(int vertices) {
        return new Graph(vertices, 2);
    }

    public static Graph generate3DGraph(int vertices) {
        return new Graph(vertices, 3);
    }

    public static Graph generate4DGraph(int vertices) {
        return new Graph(vertices, 4);
    }

}

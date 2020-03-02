import org.junit.*;

import java.util.Map;
import java.util.Random;

public class Test {
    @org.junit.Test
    public static void testGraph() {

        System.out.println("Passed testGraph().");
    }
    /*
    public static void testGraphGenerator() {
        Graph g = GraphGenerator.generate1DGraph(10);
        g = GraphGenerator.generate2DGraph(10);
        g = GraphGenerator.generate3DGraph(10);
        g = GraphGenerator.generate4DGraph(10);

        System.out.println("Passed testGraphGenerator().");

    }
    */



    public static void testED() {
        float[] start = new float[] {5.0f, 1.0f};
        float[] end = new float[] {3.0f, 2.0f};
        float result = GraphGenerator.euclideanDistance(start, end);

        Assert.assertEquals(2.236, result, 0.01);
    }

    public static void test1D() {
        randmst.run1D(6, 1);
    }

    public static void test4D() {
        randmst.run4D(128, 5);
    }


    public static void main(String[] args) {
        randmst.run2D(128, 5);
        randmst.run4D(128, 5);
        randmst.run3D(128, 5);
        float f = 100f;
        System.out.println(f / (float) 10);


    }
}

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
        float[] start = new float[] {0.9f, 0.8f, 0.7f, 0.6f};
        float[] end = new float[] {0.2f, 0.3f, 0.4f, 0.5f};
        float result = GraphGenerator.euclideanDistance(start, end);
        System.out.println(result);

        Assert.assertEquals(0.2,  result, 0.01);
    }

    public static void test4D() {
        randmst.run4D(128, 5);
    }


    public static void main(String[] args) {
        //testED();
        //randmst.run2D(5, 1);
        //randmst.run3D(5, 1);
        randmst.run4D(5, 3);


    }
}

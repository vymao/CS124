import org.junit.*;

import java.util.Map;

public class Test {
    @org.junit.Test
    public static void testGraph() {
        Graph g = new Graph(5);
        g.setEdge(1, 2, 0.1f);
        g.setEdge(2, 3, 0.2f);
        g.setEdge(2, 4, 0.3f);
        g.setEdge(3,5,0.4f);
        g.setEdge(4, 5, 0.5f);
        g.setEdge(1, 5, 0.6f);

        ///Assert.assertEquals(1, 1, 0.0001);

        Assert.assertEquals(g.getEdge(1, 2), 0.1, 0.0001);
        Assert.assertEquals(g.getEdge(2, 3), 0.2, 0.0001);
        Assert.assertEquals(g.getEdge(2, 4), 0.3, 0.0001);
        Assert.assertEquals(g.getEdge(3, 5), 0.4, 0.0001);
        Assert.assertEquals(g.getEdge(1, 5), 0.6, 0.0001);
        Assert.assertEquals(g.getEdge(4, 5), 0.5, 0.0001);

        for (Map.Entry<Integer, Float> entry : g.adj_matrix.data.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }

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
    public static void testMST() {
        MST m = new MST();
        Graph g = new Graph(5);
        g.setEdge(1, 2, 0.1f);
        g.setEdge(2, 3, 0.2f);
        g.setEdge(2, 4, 0.3f);
        g.setEdge(3,5,0.4f);
        g.setEdge(4, 5, 0.5f);
        g.setEdge(1, 5, 0.6f);
        g.setEdge(4, 3, 0.1f);

        double sum = m.runMST(g, 5);

        Assert.assertEquals(1.0, sum, 0.00001);


        System.out.println("Passed testMST().");


    }

    public static void testED() {
        float[] start = new float[] {5.0f, 1.0f};
        float[] end = new float[] {3.0f, 2.0f};
        float result = GraphGenerator.euclideanDistance(start, end);

        Assert.assertEquals(2.236, result, 0.01);
    }

    public static void testMatrix() {
        Graph.SparseMatrix<Float> matrix =
                new Graph.SparseMatrix<Float>(100000, 100000, 0.0F);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 100000; j++) {
                matrix.setValueAt(i, j,42.0F );
            }
        }
        //matrix.setValueAt(1000, 1001, 42.0F);
        Assert.assertEquals(42.0, matrix.getValueAt(1000,1001), 0.001f);
        Assert.assertEquals(0.0, matrix.getValueAt(1001,1000), 0.001f);

    }

    public static void main(String[] args) {
        testGraph();
        //testGraphGenerator();
        testMST();
        testED();
        testMatrix();

    }
}

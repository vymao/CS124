import java.util.*;

public class Main {
    public static void main(String[] args) {
        MST mst = new MST();
        GraphGenerator g = new GraphGenerator();
        int vertices =  262144;
         /*
        Graph graph1 = g.generate1DGraph(vertices);

        float test = mst.runMST(graph1, vertices);
        System.out.println(test);

        /*
        graph1 = g.generate2DGraph(vertices);

        test = mst.runMST(graph1, vertices);
        System.out.println(test);


        graph1 = g.generate3DGraph(vertices);

        test = mst.runMST(graph1, vertices);
        System.out.println(test);
        */

        Graph graph1 = g.generate4DGraph(vertices);

        float test = mst.runMST(graph1, vertices);
        System.out.println(test);


    }
}

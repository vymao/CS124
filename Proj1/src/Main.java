import java.util.*;

public class Main {
    public static void constructCutOff1D() {
        GraphGenerator g = new GraphGenerator();
        //double[] results = new double[999];
        for (int i = 1; i < 2000; i = i + 10) {
            float trial_sum = 0;
            for (int j = 1; j < 5; j++) {
                MST mst = new MST();
                int vertices = i;
                Graph graph1 = g.generate2DGraph(vertices);
                mst.runMST(graph1, vertices);
                trial_sum += mst.maxEdge();
            }
            System.out.println(trial_sum / 5);
        }
    }
    public static void main(String[] args) {
        GraphGenerator g = new GraphGenerator();
        //double[] results = new double[999];
        MST_Edge mst = new MST_Edge();
        int vertices = 6;;
        float sum = mst.runMST(vertices);
        System.out.println(sum);

       // constructCutOff1D();
          /*
        GraphGenerator g = new GraphGenerator();
        //double[] results = new double[999];
        MST mst = new MST();
        int vertices = 1;
        Graph graph1 = g.generate1DGraph(vertices);
        mst.runMST(graph1, vertices);
        System.out.println(mst.maxEdge());

        MST mst = new MST();
        GraphGenerator g = new GraphGenerator();
        int vertices =  262144;

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


        Graph graph1 = g.generate4DGraph(vertices);

        float test = mst.runMST(graph1, vertices);
        System.out.println(test);

        */


    }
}

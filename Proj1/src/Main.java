import org.omg.CORBA.INTERNAL;

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
                mst.findMST(graph1, vertices);
                trial_sum += mst.maxEdge();
            }
            System.out.println(trial_sum / 5);
        }
    }
    public static void main(String[] args) {

        /*
        GraphGenerator g = new GraphGenerator();

        //double[] results = new double[999];
        MST mst = new MST();
        int vertices = 262144;
        Graph graph1 = g.generate4DGraph(vertices);
        float sum = mst.findMST(graph1, vertices);
        System.out.println(sum);


        for (int i = 1; i <= 18; i++) {

            final int  n          = 1 << i;
            long total_time = 0;
            float total_sum = 0;

            for (int j = 1; j <= 5; j++) {
                final long t0 =  System.nanoTime();
                MST_Edge mst = new MST_Edge();
                total_sum += mst.runMST(n);
                long endTime = System.currentTimeMillis();
                total_time += (System.nanoTime() - t0);
            }
            System.out.println("N = " + Integer.toString(n) +": " + Float.toString(total_sum / 5f));
            System.out.println("N = " + n + ": " + ((double) total_time / 1000 / 1000 / 1000 / 5) + " s");
        }

         */
        System.out.println("Running 2D graph test...");
        for (int i = 1; i <= 18; i++) {

            final int  n          = 1 << i;
            long total_time = 0;
            float total_sum = 0;

            for (int j = 1; j <= 5; j++) {
                GraphGenerator g = new GraphGenerator();
                final long t0 =  System.nanoTime();
                MST mst = new MST();
                Graph graph1 = g.generate2DGraph(n);
                total_sum += mst.findMST(graph1, n);
                long endTime = System.currentTimeMillis();
                total_time += (System.nanoTime() - t0);
            }
            System.out.println("N = " + Integer.toString(n) +": " + Float.toString(total_sum / 5f));
            System.out.println("N = " + n + ": " + ((double) total_time / 1000 / 1000 / 1000 / 5) + " s");
        }
        System.out.println("Running 3D graph test...");
        for (int i = 1; i <= 18; i++) {

            final int  n          = 1 << i;
            long total_time = 0;
            float total_sum = 0;

            for (int j = 1; j <= 5; j++) {
                GraphGenerator g = new GraphGenerator();
                final long t0 =  System.nanoTime();
                MST mst = new MST();
                Graph graph1 = g.generate3DGraph(n);
                total_sum += mst.findMST(graph1, n);
                long endTime = System.currentTimeMillis();
                total_time += (System.nanoTime() - t0);
            }
            System.out.println("N = " + Integer.toString(n) +": " + Float.toString(total_sum / 5f));
            System.out.println("N = " + n + ": " + ((double) total_time / 1000 / 1000 / 1000 / 5) + " s");
        }
        System.out.println("Running 4D graph test...");
        for (int i = 1; i <= 18; i++) {

            final int  n          = 1 << i;
            long total_time = 0;
            float total_sum = 0;

            for (int j = 1; j <= 5; j++) {
                GraphGenerator g = new GraphGenerator();
                final long t0 =  System.nanoTime();
                MST mst = new MST();
                Graph graph1 = g.generate4DGraph(n);
                total_sum += mst.findMST(graph1, n);
                long endTime = System.currentTimeMillis();
                total_time += (System.nanoTime() - t0);
            }
            System.out.println("N = " + Integer.toString(n) +": " + Float.toString(total_sum / 5f));
            System.out.println("N = " + n + ": " + ((double) total_time / 1000 / 1000 / 1000 / 5) + " s");
        }



    }
}

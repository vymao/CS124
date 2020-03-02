import java.util.*;

public class randmst {

    private static int numVertices, numtrials, dimensions;

    public static void run0D(int vertices, int trials) {
        long total_time = 0;
        float total_sum = 0;

        for (int j = 1; j <= trials; j++) {
            final long t0 =  System.nanoTime();
            MST_Edge mst = new MST_Edge();
            total_sum += mst.runMST(vertices);
            total_time += (System.nanoTime() - t0);
        }
        System.out.println("N = " + Integer.toString(vertices) +" sum: " + Float.toString(total_sum / (float) trials));
        System.out.println("N = " + vertices + " time: " + ((double) total_time / 1000 / 1000 / 1000 / trials) + " s");

    }

    public static void run2D(int vertices, int trials) {
        long total_time = 0;
        float total_sum = 0;

        for (int j = 1; j <= trials; j++) {
            GraphGenerator g = new GraphGenerator();
            final long t0 =  System.nanoTime();
            MST mst = new MST();
            Graph graph1 = g.generate2DGraph(vertices);
            total_sum += mst.findMST(graph1, vertices);
            total_time += (System.nanoTime() - t0);
        }
        System.out.println("N = " + Integer.toString(vertices) +" sum: " + Float.toString(total_sum / (float) trials));
        System.out.println("N = " + vertices + " time: " + ((double) total_time / 1000 / 1000 / 1000 / trials) + " s");
    }

    public static void run3D(int vertices, int trials) {
        long total_time = 0;
        float total_sum = 0;

        for (int j = 1; j <= trials; j++) {
            GraphGenerator g = new GraphGenerator();
            final long t0 =  System.nanoTime();
            MST mst = new MST();
            Graph graph1 = g.generate3DGraph(vertices);
            total_sum += mst.findMST(graph1, vertices);
            total_time += (System.nanoTime() - t0);
        }
        System.out.println("N = " + Integer.toString(vertices) +" sum: " + Float.toString(total_sum / (float) trials));
        System.out.println("N = " + vertices + " time: " + ((double) total_time / 1000 / 1000 / 1000 / trials) + " s");
    }

    public static void run4D(int vertices, int trials) {
        long total_time = 0;
        float total_sum = 0;

        for (int j = 1; j <= trials; j++) {
            GraphGenerator g = new GraphGenerator();
            final long t0 =  System.nanoTime();
            MST mst = new MST();
            Graph graph1 = g.generate4DGraph(vertices);
            total_sum += mst.findMST(graph1, vertices);
            total_time += (System.nanoTime() - t0);
        }
        System.out.println("N = " + Integer.toString(vertices) +" sum: " + Float.toString(total_sum / (float) trials));
        System.out.println("N = " + vertices + " time: " + ((double) total_time / 1000 / 1000 / 1000 / trials) + " s");
    }

    public static void runAll() {

        System.out.println("Running 1D graph test...");
        for (int i = 1; i <= 9; i++) {
            final int  n          = 1 << i;
            run0D(n, 5);
        }





        System.out.println("Running 2D graph test...");
        for (int i = 0; i <= 18; i++) {

            final int  n          = 1 << i;
            run2D(n, 5);
        }


        System.out.println("Running 3D graph test...");
        for (int i = 0; i <= 10; i++) {

            int  n          = 1 << i;
            run3D(n, 5);
        }






        System.out.println("Running 4D graph test...");
        for (int i = 0; i <= 18; i++) {
            int  n          = 1 << i;
            run4D(n, 5);
        }


    }

    private static void checkArguments(String input, int number) {
        switch(number) {
            case 0:
                try {
                    numVertices = Integer.parseInt(input);
                } catch (Exception e) {
                    System.out.println("Invalid input for number of vertices.");
                }
                break;
            case 1:
                try {
                    numtrials = Integer.parseInt(input);
                } catch (Exception e) {
                    System.out.println("Invalid input for number of trials.");
                }
                break;
            case 2:
                try {
                    dimensions = Integer.parseInt(input);
                } catch (Exception e) {
                    System.out.println("Invalid input for number of dimensions.");
                }
                break;
            default:
                System.out.println("Invalid parameters.");
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Default parameters: ");
            System.out.println("Maximum number of vertices: 262144");
            System.out.println("Number of trials: 5");
            System.out.println("Maximum number of dimensions: 4");
            System.out.println("Running all tests...");
            runAll();
        } else {
            for (int i = 0; i < args.length; i++) {
                checkArguments(args[i], i);
            }
            System.out.println(numtrials);
            System.out.println(numVertices);
            System.out.println(dimensions);
            switch(dimensions) {
                case 0:
                    run0D(numVertices, numtrials);
                    break;
                case 2:
                    run2D(numVertices, numtrials);
                    break;
                case 3:
                    run3D(numVertices, numtrials);
                    break;
                case 4:
                    run4D(numVertices, numtrials);
                    break;
                default:
                    System.out.println("Invalid.");
            }
        }

    }
}

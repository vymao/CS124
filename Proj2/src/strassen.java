import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class strassen {
    private static int[][] m1, m2;
    public static int runSolverMain() {
        for (int i = 2; i < 200; i ++) {
            //int  n          = 1 << i;
            int[][] m1 = new int[i][i];
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < i; k++) {
                    Random generator = new Random();
                    m1[j][k] = generator.nextInt(1);
                }
            }
            int[][] m2 = m1.clone();

            /*
            long t0 =  System.nanoTime();
            Solver.Standard(m1, m2);
            double standard_time = (double) (System.nanoTime() - t0) / 1000 / 1000 / 1000;

            long t1 =  System.nanoTime();
            Solver.runStrassen(m1, m2, i / 2);
            double strassen_time = (double) (System.nanoTime() - t1) / 1000 / 1000 / 1000;

             */

            long standard_total_time = 0;
            long strassen_total_time = 0;
            for (int j = 1; j <= 5; j++) {
                long t0 =  System.nanoTime();
                Solver.Standard(m1, m2);
                standard_total_time += (System.nanoTime() - t0);

                long t1 =  System.nanoTime();
                Solver.runStrassen(m1, m2, i / 2);
                strassen_total_time += (System.nanoTime() - t1);
            }
            double standard_time = (double) standard_total_time / 1000 / 1000 / 1000 / 5;
            double strassen_time = (double) strassen_total_time / 1000 / 1000 / 1000 / 5;



            System.out.println(i);
            System.out.println("Standard with Strassen's: " + Double.toString(strassen_time));
            System.out.println("Standard: " + Double.toString(standard_time));
            System.out.println();

            //if (strassen_time < standard_time) {
            //    return i;
            //}
        }
        return 0;
    }

    public static void runTriangleGraph() {
        float average_sum = 0;
        for (int j = 1; j <= 5; j++) {
            average_sum += (float) TriangleGraph.countTriangles(0.01f);
        }
        System.out.println("p = 0.01: " + Float.toString(average_sum));

        average_sum = 0;
        for (int j = 1; j <= 5; j++) {
            average_sum += (float) TriangleGraph.countTriangles(0.02f);
        }
        System.out.println("p = 0.02: " + Float.toString(average_sum));

        average_sum = 0;
        for (int j = 1; j <= 5; j++) {
            average_sum += (float) TriangleGraph.countTriangles(0.03f);
        }
        System.out.println("p = 0.03: " + Float.toString(average_sum));

        average_sum = 0;
        for (int j = 1; j <= 5; j++) {
            average_sum += (float) TriangleGraph.countTriangles(0.04f);
        }
        System.out.println("p = 0.04: " + Float.toString(average_sum));

        average_sum = 0;
        for (int j = 1; j <= 5; j++) {
            average_sum += (float) TriangleGraph.countTriangles(0.05f);
        }
        System.out.println("p = 0.05: " + Float.toString(average_sum));
    }

    private static void readLargerTextFile(String fileName, int dim) throws IOException {
        int i = 0;
        int j = 0;
        int index = 0;
        Path path = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(path)){
            String line = null;
            while ((line = reader.readLine()) != null) {
                int num = Integer.parseInt(String.valueOf(line));
                if (index <= dim * dim) {
                    m1[i][j] = num;
                } else {
                    m2[i][j] = num;
                }
                if (j == dim) {
                    j = 0;
                    i += 1;
                }
                index += 1;
            }
        }
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Must imput parameters: dimension and source file.");
        } else if (args.length == 1) {
            System.out.println("Missing input");
        } else {
            int dim = Integer.parseInt(args[0]);
            if (dim <= 0) {
                System.out.println("Invalid dimension.");
            }

            try {
                readLargerTextFile(args[1], dim);
                int[][] answer = Solver.runStrassen(m1, m2, 15);
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        System.out.println(answer[i][j]);
                    }
                }
                System.out.println();
            } catch (Exception e){
                System.out.println("Error");
            }


        }


    }
}

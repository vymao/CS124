import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class runTasks {
    public static void PrintDataToTabDelimitedFile(ArrayList n1, ArrayList n2, ArrayList n3, ArrayList n4, String filename)
    {
        try {
// Tab delimited file will be written to data with the name tab-file.csv
            FileWriter fos = new FileWriter(filename, true);
            PrintWriter dos = new PrintWriter(fos);
           // dos.println("Matrix Size\tCutoff\tStandard with Strassen's\tStandard");
// loop through all your data and print it to the file
            for (int i=0;i<n1.size();i++) {
                dos.print(n1.get(i) + "\t");
                dos.print(n2.get(i) + "\t");
                dos.print(n3.get(i) + "\t");
                dos.print(n4.get(i) + "\t");
                dos.println();
            }
            dos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Error Printing Tab Delimited File");
        }
    }

    public static void PrintDataToTabDelimitedFile(ArrayList n1, ArrayList n2, ArrayList n3, String filename)
    {
        try {
// Tab delimited file will be written to data with the name tab-file.csv
            FileWriter fos = new FileWriter(filename, true);
            PrintWriter dos = new PrintWriter(fos);
            // dos.println("Matrix Size\tCutoff\tStandard with Strassen's\tStandard");
// loop through all your data and print it to the file
            for (int i = 0;i < n1.size();i++) {
                dos.print(n1.get(i) + "\t");
                dos.print(n2.get(i) + "\t");
                dos.print(n3.get(i) + "\t");
                dos.println();
            }
            dos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Error Printing Tab Delimited File");
        }
    }


    public static void runTriangleGraph() {
        float average_sum = 0;
        for (int j = 1; j <= 5; j++) {
            average_sum += (float) TriangleGraph.countTriangles(0.01f);
        }
        System.out.println("p = 0.01: " + Float.toString(average_sum / 5));

        average_sum = 0;
        for (int j = 1; j <= 5; j++) {
            average_sum += (float) TriangleGraph.countTriangles(0.02f);
        }
        System.out.println("p = 0.02: " + Float.toString(average_sum / 5));

        average_sum = 0;
        for (int j = 1; j <= 5; j++) {
            average_sum += (float) TriangleGraph.countTriangles(0.03f);
        }
        System.out.println("p = 0.03: " + Float.toString(average_sum / 5));

        average_sum = 0;
        for (int j = 1; j <= 5; j++) {
            average_sum += (float) TriangleGraph.countTriangles(0.04f);
        }
        System.out.println("p = 0.04: " + Float.toString(average_sum / 5));

        average_sum = 0;
        for (int j = 1; j <= 5; j++) {
            average_sum += (float) TriangleGraph.countTriangles(0.05f);
        }
        System.out.println("p = 0.05: " + Float.toString(average_sum / 5));
    }

    public static int runSolverMain() {

        for (int i = 0; i < 2; i++) {
            for (int j = 2; j <= 200; j += 1) {
                int[][] m1 = new int[j][j];
                for (int m = 0; m < j; m++) {
                    for (int k = 0; k < j; k++) {
                        Random generator = new Random();
                        m1[m][k] = generator.nextInt(1);
                    }
                }
                int[][] m2 = m1.clone();

                long standard_total_time = 0;
                long strassen_total_time = 0;
                for (int m = 1; m <= 10; m++) {
                    strassen.Solver solver = new strassen().new Solver();
                    long t0 = System.nanoTime();
                    solver.Standard(m1, m2);
                    standard_total_time += (System.nanoTime() - t0);

                    solver = new strassen().new Solver();
                    long t1 = System.nanoTime();
                    solver.runStrassen(m1, m2, (int) Math.ceil((double) j / 2));
                    strassen_total_time += (System.nanoTime() - t1);
                }
            }

        }


        ///////


        ArrayList n1, n2, n3, n4;
        for (int i = 0; i < 3; i++) {
            n1 = new ArrayList();
            n2 = new ArrayList();
            n3 = new ArrayList();
            n4 = new ArrayList();
            for (int j = 2; j <= 200; j += 1) {
                int[][] m1 = new int[j][j];
                int[][] m2 = m1.clone();
                for (int m = 0; m < j; m++) {
                    for (int k = 0; k < j; k++) {
                        Random generator = new Random();
                        if (i == 0) {
                            m1[m][k] = generator.nextInt(2);
                        } else if (i == 1) {
                            m1[m][k] = generator.nextInt(3);
                        } else {
                            m1[m][k] = generator.nextInt(3) - 1;
                        }
                        m2[m][k] = m1[m][k];
                    }
                }

                long standard_total_time = 0;
                long strassen_total_time = 0;
                double standard_time = 0;
                double strassen_time = 0;


                for (int a = 1; a <= 100; a++) {
                    strassen.Solver solver = new strassen().new Solver();
                    long t0 = System.nanoTime();
                    solver.Standard(m1, m2);
                    standard_total_time += (System.nanoTime() - t0);

                    solver = new strassen().new Solver();
                    long t1 = System.nanoTime();
                    solver.runStrassen(m1, m2, (int) Math.ceil((double) j / 2));
                    strassen_total_time += (System.nanoTime() - t1);
                }

                standard_time += (double) standard_total_time / 1000 / 1000 / 1000 / 100;
                strassen_time += (double) strassen_total_time / 1000 / 1000 / 1000 / 100;



                standard_time = standard_time / 3;
                strassen_time = strassen_time / 3;

                n1.add(200);
                n2.add(j);
                n3.add(strassen_time);
                n4.add(standard_time);

            }
            if (i == 0) {
                String filename = "/Users/vmao/Desktop/CS124/Proj2/StrassenTestOutput2_0_1.txt";
                PrintDataToTabDelimitedFile(n1, n2, n3, n4, filename);
            } else if (i == 1) {
                String filename = "/Users/vmao/Desktop/CS124/Proj2/StrassenTestOutput2_0_1_2.txt";
                PrintDataToTabDelimitedFile(n1, n2, n3, n4, filename);
            } else {
                String filename = "/Users/vmao/Desktop/CS124/Proj2/StrassenTestOutput2_N1_0_1.txt";
                PrintDataToTabDelimitedFile(n1, n2, n3, n4, filename);
            }
        }
        return 0;
    }



    public static long recursiveStrassen(long start, long end) {
        if (start <= end) {
            return 2 * (long) Math.pow(start, 3) - (long) Math.pow(start, 2);
        } else {
            if (start % 2 == 0) {
                //System.out.println(start / 2);
                return 7 * (recursiveStrassen(start / 2, end)) + 18 * (long) Math.pow(start, 2);
            } else {
                //System.out.println((start + 1) / 2);
                return 7 * (recursiveStrassen((start + 1) / 2, end))  + 18 * (long) Math.pow(start, 2);
            }
        }
    }

    public static void EquationSolver() {

        ArrayList n1, n2, n3, n4;

        /*
        int[] tests = new int[6];
        tests[0] = 128;
        tests[1] = 129;
        tests[2] = 200;
        tests[3] = 400;
        tests[4] = 500;
        tests[5] = 800;

         */
        int[] tests = new int[1];
        tests[0] = 200;


        for (int i = 0; i < tests.length; i++) {
            n1 = new ArrayList();
            n2 = new ArrayList();
            n3 = new ArrayList();
            n4 = new ArrayList();
            int standard = 2 * (int) Math.pow(tests[i], 3) - (int) Math.pow(tests[i], 2);

            for (int j = 1; j <= tests[i]; j += 1) {
                n1.add(tests[i]);
                n2.add(j);
                //long analytical_time = recursiveStrassen(tests[i], j);
                long analytical_time = recursiveStrassen(j, (long) Math.ceil((double) j / 2));
                n3.add(analytical_time);
                n4.add(2 * (long) Math.pow(j, 3) - (long) Math.pow(j, 2));

            }

            String output = "/Users/vmao/Desktop/CS124/Proj2/NumericalRuntimes.txt";
            PrintDataToTabDelimitedFile(n1, n2, n3, n4, output);

        }
    }

    public static void main(String[] args) {
        runSolverMain();
        //runTriangleGraph();
        //TriangleGraph.countTriangles(0.02f);
        //EquationSolver();

    }

}

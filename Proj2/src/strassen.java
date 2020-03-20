import java.util.Random;

public class strassen {
    public static int runSolverMain() {
        for (int i = 1; i < 15; i++) {
            int  n          = 1 << i;
            int[][] m1 = new int[n][n];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    Random generator = new Random();
                    m1[j][k] = generator.nextInt(1);
                }
            }
            int[][] m2 = m1.clone();

            long t0 =  System.nanoTime();
            Solver.Standard(m1, m2);
            double standard_time = (double) (System.nanoTime() - t0) / 1000 / 1000 / 1000;

            long t1 =  System.nanoTime();
            Solver.runStrassen(m1, m2);
            double strassen_time = (double) (System.nanoTime() - t1) / 1000 / 1000 / 1000;

            /*
                       long standard_total_time = 0;
            long strassen_total_time = 0;
            for (int j = 1; j <= 5; j++) {
                long t0 =  System.nanoTime();
                Solver.Standard(m1, m2);
                standard_total_time += (System.nanoTime() - t0);

                long t1 =  System.nanoTime();
                Solver.runStrassen(m1, m2);
                strassen_total_time += (System.nanoTime() - t1);
            }
            double standard_time = (double) standard_total_time / 1000 / 1000 / 1000 / 5;
            double strassen_time = (double) strassen_total_time / 1000 / 1000 / 1000 / 5;

             */

            System.out.println(n);
            System.out.println("Strassen's: " + Double.toString(strassen_time));
            System.out.println("Standard: " + Double.toString(standard_time));
            System.out.println();

            //if (strassen_time < standard_time) {
            //    return i;
            //}
        }
        return 0;
    }

    public static void main(String[] args) {
        //CutoffSolver.EquationSolver();

        int answer = runSolverMain();
        System.out.println(answer);

    }
}

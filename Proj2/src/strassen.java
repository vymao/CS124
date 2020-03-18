import java.util.Random;

public class strassen {
    public static int runSolverMain() {
        for (int i = 2; i < 200; i++) {
            int[][] m1 = new int[i][i];
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < i; k++) {
                    Random generator = new Random();
                    m1[j][k] = generator.nextInt(1);
                }
            }
            int[][] m2 = m1.clone();

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

            System.out.println(strassen_time);
            System.out.println(standard_time);

            //if (strassen_time < standard_time) {
             //   return i;
            //}
        }
        return 0;
    }

    public static void main(String[] args) {
        CutoffSolver.EquationSolver();

        int answer = runSolverMain();
        System.out.println(answer);

    }
}

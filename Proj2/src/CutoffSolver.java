import java.lang.Math.*;

public class CutoffSolver {

    public static int recursiveStrassen(int start, int end) {
        if (start <= end) {
            return 2 * start^3 - start^2;
        } else {
            if (start % 2 == 0) {
                return 7 * (recursiveStrassen(start / 2, end)) + 18 * start^2;
            } else {
                return 7 * (recursiveStrassen((start + 1) / 2, end));
            }
        }
    }

    public static void EquationSolver() {
        int[] tests = new int[5];
        tests[0] = 128;
        tests[1] = 129;
        tests[2] = 200;
        tests[3] = 400;
        tests[4] = 800;

        for (int i = 0; i < tests.length; i ++) {
            for (int j = 0; j < tests[i]; j++) {
                int analytical_time = recursiveStrassen(tests[i], j);
            }
        }






        /*
        for (int i = 1; i <= 15; i++) {
            int  n          = 1 << i;
            System.out.println((long) 2 * Math.pow(n, 3) - Math.pow(n, 2));
        }

         */

        long T = 1;
        for (int i = 2; i <= 100; i = i + 2) {
            //int  n          = 1 << i;
            long T_current = (7 * T) + (long) Math.pow(i/ 2, 2) * 18;
            System.out.println("N = " + Integer.toString(i));
            //System.out.println("Strassens's: " + Long.toString(T_current));
            System.out.println("Standard MM: " + Double.toString(2 * Math.pow(i, 3) - Math.pow(i, 2)));
            System.out.println("Standard MM with Strassen's: " + Double.toString((7 * (2 * Math.pow(i / 2, 3) - Math.pow(i / 2, 2))) + (long) Math.pow(i / 2, 2) * 18));
            System.out.println();
            T = T_current;
        }
    }
}

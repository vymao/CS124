import java.lang.Math.*;

public class CutoffSolver {

    public static void EquationSolver() {
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

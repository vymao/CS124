import java.util.*;

public class Fibonacci_Methods {
    static int correction = 65536;

    public static int Fib_recursive(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return ((Fib_recursive(n - 1) + Fib_recursive(n - 2)) % correction);
        }
    }

    public static int Fib_iter(long n) {
        int ppNum, pNum = 0, current = 1;

        for (int i = 1; i < n ; i++) {
            ppNum = pNum;
            pNum = current;

            current = (ppNum + pNum) % correction;
        }
        return current;
        ///return sequence[n];
    }

    public static long[][] Matrix_Mult(long[][] m1, long[][] m2) {
        long[][] matrix1, matrix2;

        if (!((m1[0].length == m2.length) || m2[0].length == m1.length)) {
            return null;
        } else if (m1[0].length == m2.length) {
            matrix1 = m1;
            matrix2 = m2;
        } else {
            matrix1 = m2;
            matrix2 = m1;
        }

        long[][] product = new long[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                long sum = 0;

                for (int k = 0; k < matrix1[0].length; k++) {
                    sum = (sum + ((matrix1[i][k] * matrix2[k][j]) % correction)) % correction;
                }

                product[i][j] = sum;
            }
        }

        return product;
    }

    public static int Fib_matrix(long n) {
        if (n == 0) {
            return 0;
        } else {
            long[][] FibMatrix = {{0, 1}, {1, 1}};
            long[][] product = {{1, 0}, {0, 1}};

            while (n > 0) {
                if (n % 2 == 1) {
                    //valid bit
                    product = Matrix_Mult(product, FibMatrix);
                }

                n = n / 2;
                FibMatrix = Matrix_Mult(FibMatrix, FibMatrix);

            }

            long[][] Fib_start = {{0}, {1}};
            long[][] Fib = Matrix_Mult(product, Fib_start);

            return (int) Fib[0][0];
        }
    }

    public static int[] run_test(double time) {
        long startTime, endTime;
        int n = 1;
        int[] times = new int[3];

        while (n > 0) {
            startTime = System.nanoTime();
            int iter_sol = Fib_recursive(n);
            endTime = System.nanoTime();
            double timed = (double) (endTime - startTime) / ((double) 1000000000);
            if (timed > time) {
                times[0] = n - 1;
                System.out.println("Recursive maximum in 10 seconds: " + Integer.toString(n - 1));
                break;
            }
            n++;
        }

        while (n > 0) {
            startTime = System.nanoTime();
            int iter_sol = Fib_iter(n);
            endTime = System.nanoTime();
            double timed = (double) (endTime - startTime) / ((double) 1000000000);
            if (timed > time) {
                times[1] = n - 1;
                System.out.println("Iterative maximum in 10 seconds: " + Integer.toString(n - 1));
                break;
            }
            n++;
        }

        while (n > 0) {
            startTime = System.nanoTime();
            int iter_sol = Fib_matrix(n);
            endTime = System.nanoTime();
            double timed = (double) (endTime - startTime) / ((double) 1000000000);
            if (timed > time) {
                times[2] = n - 1;
                System.out.println("Matrix maximum in 10 seconds: " + Integer.toString(n - 1));
                break;
            }
            n++;
        }

        return times;

    }


    public static void main(String[] args) {
        long startTime, endTime;
        /*
        startTime = System.nanoTime();
        int recursive_solution = Fib_recursive(Integer.parseInt(args[0]));
        endTime = System.nanoTime();
        double Fib_recursive_time = ((double) (endTime - startTime) / ((double) 1000000000));
        System.out.println("Recursive solution: " + Integer.toString(recursive_solution));
        System.out.println("Recursive solution time: " + Double.toString(Fib_recursive_time) + " seconds");

        startTime = System.nanoTime();
        int iter_solution = Fib_iter(Long.parseLong(args[0]));
        endTime = System.nanoTime();
        double Fib_iter_time = (double) ((double) (endTime - startTime) / ((double) 1000000000));
        System.out.println("Iterative solution: " + Integer.toString(iter_solution));
        System.out.println("Iterative solution time: " + Double.toString(Fib_iter_time) + " seconds");
*/
        startTime = System.nanoTime();
        int matrix_solution = Fib_matrix(Long.parseLong(args[0]));
        endTime = System.nanoTime();
        double Fib_matrix_time = (double) ((double) (endTime - startTime) / ((double) 1000000000));
        System.out.println("Matrix solution: " + Integer.toString(matrix_solution));
        System.out.println("Matrix solution time: " + Double.toString(Fib_matrix_time) + " seconds");

        //int[] times = run_test(10);
    }

}

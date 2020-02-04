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
        /*
        int[] sequence = new int[n + 1];
        sequence[0] = 0;
        sequence[1] = 1;
        for (int i = 2; i <= n; i++) {
            sequence[i] = (sequence[i - 1] + sequence[i - 2]) % correction;
        }
        */
        int previouspreviousNumber, previousNumber = 0, currentNumber = 1;

        for (int i = 1; i < n ; i++) {

            previouspreviousNumber = previousNumber;

            previousNumber = currentNumber;

            currentNumber = (previouspreviousNumber + previousNumber) % correction;

        }
        return currentNumber;
        ///return sequence[n];
    }

    public static int[][] Matrix_Mult(int[][] m1, int[][] m2) {
        int[][] matrix1, matrix2;

        if (!((m1[0].length == m2.length) || m2[0].length == m1.length)) {
            return null;
        } else if (m1[0].length == m2.length) {
            matrix1 = m1;
            matrix2 = m2;
        } else {
            matrix1 = m2;
            matrix2 = m1;
        }

        int[][] product = new int[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                int sum = 0;

                for (int k = 0; k < matrix1[0].length; k++) {
                    sum = (sum + ((matrix1[i][k] * matrix2[k][j]) % correction)) % correction;
                }

                product[i][j] = sum;
            }
        }

        return product;
    }

    public static int Fib_matrix(int n) {
        if (n == 0) {
            return 0;
        } else {
            int[][] FibMatrix = {{0, 1}, {1, 1}};
            int[][] product = {{1, 0}, {0, 1}};

            while (n > 0) {
                if (n % 2 == 1) {
                    //valid bit
                    product = Matrix_Mult(product, FibMatrix);
                }

                n = n / 2;
                FibMatrix = Matrix_Mult(FibMatrix, FibMatrix);

            }

            int[][] Fib_start = {{0}, {1}};
            int[][] Fib = Matrix_Mult(product, Fib_start);

            return Fib[0][0];
        }
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
        */
        startTime = System.nanoTime();
        int iter_solution = Fib_iter(Long.parseLong(args[0]));
        endTime = System.nanoTime();
        double Fib_iter_time = (double) ((double) (endTime - startTime) / ((double) 1000000000));
        System.out.println("Iterative solution: " + Integer.toString(iter_solution));
        System.out.println("Iterative solution time: " + Double.toString(Fib_iter_time) + " seconds");

        startTime = System.nanoTime();
        int matrix_solution = Fib_matrix(Integer.parseInt(args[0]));
        endTime = System.nanoTime();
        double Fib_matrix_time = (double) ((double) (endTime - startTime) / ((double) 1000000000));
        System.out.println("Matrix solution: " + Integer.toString(matrix_solution));
        System.out.println("Matrix solution time: " + Double.toString(Fib_matrix_time) + " seconds");


    }

}

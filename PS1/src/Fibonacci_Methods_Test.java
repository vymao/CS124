
public class Fibonacci_Methods_Test {
    public static int compare() {
        int n = 1;
        while (n > 0) {
            int iter_sol = Fibonacci_Methods.Fib_iter(n);
            int matrix_sol = Fibonacci_Methods.Fib_matrix(n);

            if (iter_sol != matrix_sol) {
                return n;
            }

            n++;
        }
        return 0;
    }

    public static void main(String[] args) {
        /*
        int[][] m1 = {{1, 2}, {1, 2}};
        int[][] m2 = {{1, 2}, {1, 2}};
        int[][] solution = Fibonacci_Methods.Matrix_Mult(m1, m2);
        System.out.println("Solution: ");
        System.out.println(solution[0][0]);


        int[][] m3 = {{1, 2}, {1, 2}};
        int[][] m4 = {{1}, {1}};
        int[][] solution = Fibonacci_Methods.Matrix_Mult(m3, m4);
        System.out.println("Solution: ");
        System.out.println(solution[0][0]);
        */
        int solution2 = Fibonacci_Methods.Fib_matrix(61);
        System.out.println(compare());
    }
}

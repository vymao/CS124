import java.lang.reflect.Array;
import java.util.*;
import java.lang.Math.*;

public class RandomTests {
    public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

    public static void main(String[] args) {

        long int1 = 30;
        System.out.println(int1 / 2);
        System.out.println(Math.log(8) % Math.log(2));
        System.out.println(Math.ceil(Math.log(8) / Math.log(2)));

        int dim = 8;
        int[][] m1 = new int[dim][dim];
        //int[][] m1 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        //int[][] m1 = {{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}};

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                m1[i][j] = i;
            }
        }
        System.out.println(m1.length);
        int[][] m2 = m1.clone();
        System.out.println("Strassen's: ");
        print2D(Solver.runStrassen(m1, m2));
        System.out.println("Standard: ");
        print2D(Solver.Standard(m1, m2));
        CutoffSolver.EquationSolver();



    }

}

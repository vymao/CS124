import java.lang.Math.*;
import java.lang.reflect.Array;
import java.util.*;


public class Solver {
    static double log_base = Math.log(2);

    public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

    public static int[][] Standard(int[][] m1, int[][] m2) {
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
                    sum = sum + ((matrix1[i][k] * matrix2[k][j]));
                }

                product[i][j] = sum;
            }
        }

        return product;
    }

    public static int[][] padMatrix(int[][] m) {
        int nearest_power = (int) Math.ceil(Math.log(m.length) / log_base);
        int new_length = (int) Math.pow(2, nearest_power);
        int[][] new_m = new int[new_length][new_length];

        for (int i = 0; i < m.length; i++) {
            System.arraycopy(m[i], 0, new_m[i], 0, m[i].length);
        }

        return new_m;
    }

    public static int[][] matrix_subtract(int[][] m, int[][] n) {
        int[][] difference = new int[m.length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                difference[i][j] = m[i][j] - n[i][j];
            }
        }
        return difference;
    }

    public static int[][] matrix_add(int[][] m, int[][] n) {
        int[][] sum = new int[m.length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                sum[i][j] = m[i][j] + n[i][j];
            }
        }
        return sum;
    }

    public static int[][][] matrix_add_1(int cutoff, int[][][] submatrices) {
        int[][][] strassen_sums = new int[10][cutoff][cutoff];
        for (int i = 0; i < cutoff; i++) {
            for (int j = 0; j < cutoff; j++) {
                strassen_sums[0][i][j] = submatrices[5][i][j] - submatrices[7][i][j];
                strassen_sums[1][i][j] = submatrices[0][i][j] + submatrices[1][i][j];
                strassen_sums[2][i][j] = submatrices[2][i][j] + submatrices[3][i][j];
                strassen_sums[3][i][j] = submatrices[6][i][j] - submatrices[4][i][j];
                strassen_sums[4][i][j] = submatrices[0][i][j] + submatrices[3][i][j];
                strassen_sums[5][i][j] = submatrices[4][i][j] + submatrices[7][i][j];
                strassen_sums[6][i][j] = submatrices[1][i][j] - submatrices[3][i][j];
                strassen_sums[7][i][j] = submatrices[6][i][j] + submatrices[7][i][j];
                strassen_sums[8][i][j] = submatrices[0][i][j] - submatrices[2][i][j];
                strassen_sums[9][i][j] = submatrices[4][i][j] + submatrices[5][i][j];
            }
        }
        return strassen_sums;
    }

    public static int[][] matrix_add_2(int cutoff, int[][][] strassen_matrices) {
        int[][] answer = new int[cutoff * 2][cutoff * 2];
        for (int i = 0; i < cutoff; i++) {
            for (int j = 0; j < cutoff; j++) {
                answer[i][j] = strassen_matrices[4][i][j] + strassen_matrices[3][i][j] - strassen_matrices[1][i][j] + strassen_matrices[5][i][j];
                answer[i][j + cutoff] = strassen_matrices[0][i][j] + strassen_matrices[1][i][j];
                answer[i + cutoff][j] = strassen_matrices[2][i][j] + strassen_matrices[3][i][j];
                answer[i + cutoff][j + cutoff] = strassen_matrices[4][i][j] + strassen_matrices[0][i][j] - strassen_matrices[2][i][j] - strassen_matrices[6][i][j];

            }
        }
        return answer;
    }

    public static int[][] Strassen(int[][] m1, int[][] m2) {
        if (m1.length == 1) {
            int[][] answer = new int[1][1];
            answer[0][0] = m1[0][0] * m2[0][0];
            return answer;
        } else {
            int cutoff = m1.length / 2;
            //System.out.println("Cutoff: " + Integer.toString(cutoff));
            int[][][] submatrices = new int[8][cutoff][cutoff];
            for (int i = 0; i < cutoff; i++) {
                System.arraycopy(m1[i], 0, submatrices[0][i], 0, cutoff);
                System.arraycopy(m1[i], cutoff, submatrices[1][i], 0, cutoff);
                System.arraycopy(m2[i], 0, submatrices[4][i], 0, cutoff);
                System.arraycopy(m2[i], cutoff, submatrices[5][i], 0, cutoff);
            }

            for (int i = cutoff; i < m1.length; i++) {
                //System.out.println(m1.length);
                //System.out.println(i);
                System.arraycopy(m1[i], 0, submatrices[2][i - cutoff], 0, cutoff);
                System.arraycopy(m1[i], cutoff, submatrices[3][i - cutoff], 0, cutoff);
                System.arraycopy(m2[i], 0, submatrices[6][i - cutoff], 0, cutoff);
                System.arraycopy(m2[i], cutoff, submatrices[7][i - cutoff], 0, cutoff);
            }

            int[][][] strassen_matrices = matrix_add_1(cutoff, submatrices);
            strassen_matrices[0] = Strassen(submatrices[0], strassen_matrices[0]);
            strassen_matrices[1] = Strassen(strassen_matrices[1], submatrices[7]);
            strassen_matrices[2] = Strassen(strassen_matrices[2], submatrices[4]);
            strassen_matrices[3] = Strassen(submatrices[3], strassen_matrices[3]);
            strassen_matrices[4] = Strassen(strassen_matrices[4], strassen_matrices[5]);
            strassen_matrices[5] = Strassen(strassen_matrices[6], strassen_matrices[7]);
            strassen_matrices[6] = Strassen(strassen_matrices[8], strassen_matrices[9]);

            int[][] answer = matrix_add_2(cutoff, strassen_matrices);

            return answer;
        }

    }
    public static int[][] runStrassen(int[][] m1, int[][] m2) {
        if (!((m1.length & (m1.length - 1)) == 0)) {
            int original_length = m1.length;
            m1 = padMatrix(m1);
            m2 = padMatrix(m2);
            int[][] strassen = Strassen(m1, m2);
            int[][] answer = new int[original_length][original_length];
            for (int i = 0; i < original_length; i++) {
                answer[i] = Arrays.copyOf(strassen[i], original_length);
            }
            return answer;
        } else {
            return Strassen(m1, m2);
        }
    }

}

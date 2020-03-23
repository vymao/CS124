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

    public static void print2D(Matrix mat)
    {
        // Loop through all rows

        for (int i = 0; i < mat.columns; i++ ) {
            int[] row = new int[mat.columns];
            for (int j = 0; j < mat.columns; j++) {
                row[j] = mat.getValue(i, j);
            }
            System.out.println(Arrays.toString(row));
            row = new int[mat.columns];
        }
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

    public static int[][] Standard(Matrix m1, Matrix m2) {
        int[][] product = new int[m1.columns][m1.columns];
        for (int i = 0; i < m1.columns; i++) {
            for (int j = 0; j < m2.columns; j++) {
                int sum = 0;

                for (int k = 0; k < m1.columns; k++) {
                    sum = sum + (m1.getValue(i, k) * m2.getValue(k, j));
                }

                product[i][j] = sum;
            }
        }

        return product;
    }

    public static Matrix padMatrix(Matrix m) {
        Matrix new_m = new Matrix(new int[m.columns + 1][m.columns + 1]);
        for (int i = 0; i < m.columns; i++) {
            System.arraycopy(m.matrix[m.x + i], m.y, new_m.matrix[i], 0, m.columns);
        }
        //System.out.println(new_m.columns);

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

    public static int[][][] matrix_add_1(int cutoff, Matrix[] submatrices) {
        int[][][] strassen_sums = new int[10][cutoff][cutoff];
        for (int i = 0; i < cutoff; i++) {
            for (int j = 0; j < cutoff; j++) {
                strassen_sums[0][i][j] = submatrices[5].getValue(i, j) - submatrices[7].getValue(i, j);
                strassen_sums[1][i][j] = submatrices[0].getValue(i, j) + submatrices[1].getValue(i, j);
                strassen_sums[2][i][j] = submatrices[2].getValue(i, j) + submatrices[3].getValue(i, j);
                strassen_sums[3][i][j] = submatrices[6].getValue(i, j) - submatrices[4].getValue(i, j);
                strassen_sums[4][i][j] = submatrices[0].getValue(i, j) + submatrices[3].getValue(i, j);
                strassen_sums[5][i][j] = submatrices[4].getValue(i, j) + submatrices[7].getValue(i, j);
                strassen_sums[6][i][j] = submatrices[1].getValue(i, j) - submatrices[3].getValue(i, j);
                strassen_sums[7][i][j] = submatrices[6].getValue(i, j) + submatrices[7].getValue(i, j);
                strassen_sums[8][i][j] = submatrices[0].getValue(i, j) - submatrices[2].getValue(i, j);
                strassen_sums[9][i][j] = submatrices[4].getValue(i, j) + submatrices[5].getValue(i, j);
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

    public static int[][] Strassen(Matrix m1, Matrix m2, int toStandard) {
        if (m1.columns == 1) {
            int[][] answer = new int[1][1];
            answer[0][0] = m1.getValue(0, 0) * m2.getValue(0, 0);
            return answer;
        }
        //System.out.println("Before padding: ");
        //System.out.println("M1: ");
        //print2D(m1);
        //System.out.println("M2: ");
        //print2D(m2);
        if (m1.columns % 2 == 1) {
            //System.out.println((m1.columns));
            m1 = padMatrix(m1);
            m2 = padMatrix(m2);
            //print2D(m1.matrix);

        }
        int cutoff = m1.columns / 2;
        //System.out.println("After padding: ");
        //System.out.println("M1: ");
        //print2D(m1);
        //System.out.println("M2: ");
        //print2D(m2);

        //System.out.println("Cutoff: " + Integer.toString(cutoff));
        /*
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

         */
        Matrix A = m1.getSubMatrix(0, 0, cutoff);
        Matrix B = m1.getSubMatrix(0, cutoff, cutoff);
        Matrix C = m1.getSubMatrix(cutoff, 0, cutoff);
        Matrix D = m1.getSubMatrix(cutoff, cutoff, cutoff);
        Matrix E = m2.getSubMatrix(0, 0, cutoff);
        Matrix F = m2.getSubMatrix(0, cutoff, cutoff);
        Matrix G = m2.getSubMatrix(cutoff, 0, cutoff);
        Matrix H = m2.getSubMatrix(cutoff, cutoff, cutoff);

        /*
        System.out.println("A: ");
        print2D(A);
        System.out.println("B: ");
        print2D(B);
        System.out.println("C: ");
        print2D(C);
        System.out.println("D: ");
        print2D(D);
        System.out.println("E: ");
        print2D(E);
        System.out.println("F: ");
        print2D(F);
        System.out.println("G: ");
        print2D(G);
        System.out.println("H: ");
        print2D(H);

         */





        int[][] S1 = new int[cutoff][cutoff];
        int[][] S2 = new int[cutoff][cutoff];
        int[][] S3 = new int[cutoff][cutoff];
        int[][] S4 = new int[cutoff][cutoff];
        int[][] S5 = new int[cutoff][cutoff];
        int[][] S6 = new int[cutoff][cutoff];
        int[][] S7 = new int[cutoff][cutoff];
        int[][] S8 = new int[cutoff][cutoff];
        int[][] S9 = new int[cutoff][cutoff];
        int[][] S10 = new int[cutoff][cutoff];

        for (int i = 0; i < cutoff; i++) {
            for (int j = 0; j < cutoff; j++) {
                S1[i][j] = F.getValue(i, j) - H.getValue(i, j);
                S2[i][j] = A.getValue(i, j) + B.getValue(i, j);
                S3[i][j] = C.getValue(i, j) + D.getValue(i, j);
                S4[i][j] = G.getValue(i, j) - E.getValue(i, j);
                S5[i][j] = A.getValue(i, j) + D.getValue(i, j);
                S6[i][j] = E.getValue(i, j) + H.getValue(i, j);
                S7[i][j] = B.getValue(i, j) - D.getValue(i, j);
                S8[i][j] = G.getValue(i, j) + H.getValue(i, j);
                S9[i][j] = A.getValue(i, j) - C.getValue(i, j);
                S10[i][j] = E.getValue(i, j) + F.getValue(i, j);
            }
        }
        //System.out.println("S1:");
        //print2D(S1);
        int[][] P1, P2, P3, P4, P5, P6, P7;
        if (m1.columns > toStandard) {
            P1 = Strassen(A, new Matrix(S1), toStandard);
            //System.out.println("P1: ");
            //print2D(P1);
            //System.out.println("H: ");
            P2 = Strassen(new Matrix(S2), H, toStandard);
            //System.out.println("P2: ");
            //print2D(P2);
            P3 = Strassen(new Matrix(S3), E, toStandard);
            //System.out.println("P3: ");
            //print2D(P3);
            P4 = Strassen(D, new Matrix(S4), toStandard);
            //System.out.println("P4: ");
            //print2D(P4);
            P5 = Strassen(new Matrix(S5), new Matrix(S6), toStandard);
            //System.out.println("P5: ");
            //print2D(P5);
            P6 = Strassen(new Matrix(S7), new Matrix(S8), toStandard);
            //System.out.println("P6: ");
            //print2D(P6);
            P7 = Strassen(new Matrix(S9), new Matrix(S10), toStandard);
            //System.out.println("P7: ");
            //print2D(P7);
        } else {
            P1 = Standard(A, new Matrix(S1));
            P2 = Standard(new Matrix(S2), H);
            P3 = Standard(new Matrix(S3), E);
            P4 = Standard(D, new Matrix(S4));
            P5 = Standard(new Matrix(S5), new Matrix(S6));
            P6 = Standard(new Matrix(S7), new Matrix(S8));
            P7 = Standard(new Matrix(S9), new Matrix(S10));
        }

        int[][] answer = new int[m1.columns][m1.columns];

        for (int i = 0; i < cutoff; i++) {
            for (int j = 0; j < cutoff; j++) {
                answer[i][j] = P5[i][j] + P4[i][j] - P2[i][j] + P6[i][j];
                answer[i][j + cutoff] = P1[i][j] + P2[i][j];
                answer[i + cutoff][j] = P3[i][j] + P4[i][j];
                answer[i + cutoff][j + cutoff] = P5[i][j] + P1[i][j] - P3[i][j] - P7[i][j];
            }
        }
        /*
        if (odd) {
            int[][] newanswer = new int[old_length]
            for (int i = 0; i < old_length; i++) {
                int[][] actual = System.arraycopy(answer, 0, , 0, old_length);
            }

        }

         */

        //System.out.println("Answer: ");
        //print2D(answer);

        return answer;

    }

    public static int[][] runStrassen(int[][] m1, int[][] m2, int cutoff) {
        return Strassen(new Matrix(m1), new Matrix(m2), cutoff);
    }

}

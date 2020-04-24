import java.io.*;
import java.util.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;


public class strassen {
    private static int[][] m1, m2;
    final static Charset ENCODING = StandardCharsets.UTF_8;

    public class Solver {
        private class Matrix {
            int[][] matrix;
            int x, y, columns;

            public Matrix(int[][] data) {
                this(data, 0, 0, data.length);
            }

            private Matrix(int[][] data, int x, int y, int columns) {
                this.matrix = data;
                this.x = x;
                this.y = y;
                this.columns = columns;
            }

            public Matrix getSubMatrix(int x, int y, int col) {
                return new Matrix(matrix, this.x + x , this.y + y, col);
            }

            public int getValue(int row, int col) {
                return matrix[x + row][y + col];
            }
        }

        public int[][] Standard(int[][] m1, int[][] m2) {
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

        public int[][] Standard(Matrix m1, Matrix m2) {
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

        public Matrix padMatrix(Matrix m) {
            Matrix new_m = new Matrix(new int[m.columns + 1][m.columns + 1]);
            for (int i = 0; i < m.columns; i++) {
                System.arraycopy(m.matrix[m.x + i], m.y, new_m.matrix[i], 0, m.columns);
            }
            //System.out.println(new_m.columns);

            return new_m;
        }

        public int[][] Strassen(Matrix m1, Matrix m2, int toStandard) {
            if (m1.columns == 1) {
                int[][] answer = new int[1][1];
                answer[0][0] = m1.getValue(0, 0) * m2.getValue(0, 0);
                return answer;
            }

            if (m1.columns <= toStandard) {
                return Standard(m1, m2);
            }

            if (m1.columns % 2 == 1) {
                //System.out.println((m1.columns));
                m1 = padMatrix(m1);
                m2 = padMatrix(m2);
                //print2D(m1.matrix);

            }
            int cutoff = m1.columns / 2;
            Matrix A = m1.getSubMatrix(0, 0, cutoff);
            Matrix B = m1.getSubMatrix(0, cutoff, cutoff);
            Matrix C = m1.getSubMatrix(cutoff, 0, cutoff);
            Matrix D = m1.getSubMatrix(cutoff, cutoff, cutoff);
            Matrix E = m2.getSubMatrix(0, 0, cutoff);
            Matrix F = m2.getSubMatrix(0, cutoff, cutoff);
            Matrix G = m2.getSubMatrix(cutoff, 0, cutoff);
            Matrix H = m2.getSubMatrix(cutoff, cutoff, cutoff);

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
            return answer;

        }

        public int[][] runStrassen(int[][] m1, int[][] m2, int cutoff) {
            return Strassen(new Matrix(m1), new Matrix(m2), cutoff);
        }

    }


    private static void readLargerTextFile(String fileName, int dim) throws IOException {
        int i = 0;
        int j = 0;
        int index = 1;
        int m = 0;
        m1 = new int[dim][dim];
        m2 = new int[dim][dim];
        //Path path = Paths.get(fileName);
        InputStream inputStream = new FileInputStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, ENCODING))){
            //System.out.println(reader.readLine());
            String line = null;

            while (((line = reader.readLine()) != null)) {
                int num = Integer.parseInt(line);
                if (index <= dim * dim) {
                    m1[i][j] = num;
                } else {
                    m2[i][j] = num;
                }
                if (index == dim * dim) {
                    i = 0;
                    j = 0;
                } else if (j + 1 == dim) {
                    j = 0;
                    i += 1;
                } else {
                    j += 1;
                }
                index += 1;
            }
        }
    }

    public static void main(String[] args) {
        //System.out.println(args.length);

        if (args.length == 0) {
            System.out.println("Must imput parameters: dimension and source file.");
        } else if ((args.length == 1) || (args.length == 2)) {
            System.out.println("Missing input");
        } else {
            //System.out.println(args);
            int dim = Integer.parseInt(args[1]);
            if (dim <= 0) {
                System.out.println(dim);
                //System.out.println("Invalid dimension.");
            }

            try {
                //System.out.println(args[1]);
                readLargerTextFile(args[2], dim);
                Solver solver = new strassen().new Solver();
                int[][] answer = solver.runStrassen(m1, m2, 15);
                for (int i = 0; i < dim; i++) {
                    System.out.println(answer[i][i]);
                }
                //System.out.println();
            } catch (Exception e){
                System.out.println("Error");
                System.out.println(e);
            }




        }





    }
}

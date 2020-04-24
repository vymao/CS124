import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Q5 {
    public static void main(String[] args) {

    /*
        int[] distribution = new int[100];
        for (int i = 0; i < 100; i++) {
            distribution[i] = Simulation1(1000000000);
            System.out.println(distribution[i]);
        }

     */

        //String file = "/Users/vmao/Desktop/CS124/PS5/Simulation1.txt";
        //PrintDataToTabDelimitedFile(distribution, file);





        int [] distribution = new int[100];
        for (int i = 0; i < 100; i++) {
            distribution[i] = Simulation2(1000000000);
            System.out.println(distribution[i]);
        }

        //String file = "/Users/vmao/Desktop/CS124/PS5/Simulation2.txt";
        //PrintDataToTabDelimitedFile(distribution, file);





    }


    public static int Simulation1(int bins) {
        Random generator = new Random();
        ArrayList<Integer> buckets = new ArrayList<>();
        buckets.add(0, bins);
        for (int i = 0; i < bins; i++) {
            float poll = generator.nextFloat();

            int bucket_index = 0;
            float frac = 0;
            for (int j = 0; j < buckets.size(); j++) {
                frac += (float) buckets.get(j) / bins;
                if (poll <= frac) {
                    bucket_index = j;
                    break;
                }
            }

            int old = (int) buckets.get(bucket_index);
            if (bucket_index + 1 == buckets.size()) {
                buckets.add(1);
                buckets.set(bucket_index, old - 1);
            } else {
                int toSet = (int) buckets.get(bucket_index + 1);
                buckets.set(bucket_index + 1, toSet + 1);
                buckets.set(bucket_index, old - 1);
            }
        }

        int sum = 0;
        for (int a = 0; a < buckets.size(); a++) {
            sum += buckets.get(a);
        }
        return buckets.size() - 1;
    }

    public static int Simulation2(int bins) {
        Random generator = new Random();
        ArrayList<Integer> buckets = new ArrayList<>();
        buckets.add(0, bins);
        for (int i = 0; i < bins; i++) {
            float poll1 = generator.nextFloat();
            float poll2 = generator.nextFloat();

            int bucket_index_1 = 0;
            int bucket_index_2 = 0;

            float frac_1 = 0, frac_2 = 0;
            for (int j = 0; j < buckets.size(); j++) {
                frac_1 += (float) buckets.get(j) / bins;
                if (poll1 <= frac_1) {
                    bucket_index_1 = j;
                    break;
                }
            }

            for (int j = 0; j < buckets.size(); j++) {
                frac_2 += (float) buckets.get(j) / bins;
                if (poll2 <= frac_2) {
                    bucket_index_2 = j;
                    break;
                }
            }


            int old_1 = bucket_index_1;
            int old_2 = bucket_index_2;
            int bucket_index;
            if (old_1 > old_2) {
                bucket_index = bucket_index_2;
            } else {
                bucket_index = bucket_index_1;
            }
            if (bucket_index + 1 == buckets.size()) {
                buckets.add(1);
                buckets.set(bucket_index, buckets.get(bucket_index) - 1);
            } else {
                int toSet = buckets.get(bucket_index + 1);
                buckets.set(bucket_index + 1, toSet + 1);
                buckets.set(bucket_index, buckets.get(bucket_index) - 1);
            }
        }
        int sum = 0;
        for (int a = 0; a < buckets.size(); a++) {
            sum += buckets.get(a);
        }
        return buckets.size() - 1;
    }


    public static void PrintDataToTabDelimitedFile(int[] data, String filename)
    {
        try {
// Tab delimited file will be written to data with the name tab-file.csv
            FileWriter fos = new FileWriter(filename, true);
            PrintWriter dos = new PrintWriter(fos);
            // dos.println("Matrix Size\tCutoff\tStandard with Strassen's\tStandard");
// loop through all your data and print it to the file
            for (int i = 0; i < data.length; i++) {
                dos.print(Integer.toString(data[i])+ "\t");
                dos.print(Integer.toString(i) + "\t");
                dos.println();
            }
            dos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Error Printing Tab Delimited File");
        }
    }
}

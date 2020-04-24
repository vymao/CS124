import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class Q4 {

    public static void main(String[] args) {
        //System.out.println(modular_pow(3, 51, 103));
        //System.out.println(modular_pow(3, 102, 103));
        //System.out.println(-1 % 103);

        //RabinMillerTest( 294409);
        BigInteger encrypted = RSA("Give me an A", new BigInteger("46947848749720430529628739081"),  new BigInteger("37267486263679235062064536973"));
        System.out.println(encrypted);
    }

    public static BigInteger RSA(String phrase, BigInteger n, BigInteger e) {
        long num = 0;
        int multiple = 0;
        for (int i = phrase.length() - 1; i >= 0; i--) {
            char Char = phrase.charAt(i);
            int ascii = (int) Char;
            num += ascii * (1 << multiple);
            multiple += 8;
            //System.out.println(Char);
        }

        System.out.println(num);

        //System.out.println(Integer.toBinaryString((int) num));

        return modular_pow(num, e, n);
    }

    public static BigInteger modular_pow(long base, BigInteger exponent, BigInteger modulus) {
        BigInteger newbase = BigInteger.valueOf(base);

        if (modulus.equals(1)) {
            return BigInteger.valueOf(0);
        }

        BigInteger result = BigInteger.valueOf(1);
        newbase = newbase.mod(modulus);
        while (exponent.compareTo(BigInteger.valueOf(0)) > 0) {
            if (exponent.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(1))) {
                result = result.multiply(newbase).mod(modulus);
            }
            exponent = exponent.divide(BigInteger.valueOf(2));
            newbase = newbase.multiply(newbase).mod(modulus);
        }
        return result;



        /*
        if (modulus.equals(1)) {
            return BigInteger.valueOf(0);
        }
        BigInteger c = BigInteger.valueOf(1);
        for (BigInteger e_prime = BigInteger.valueOf(0); e_prime.compareTo(exponent) < 0; e_prime.add(BigInteger.valueOf(1))) {
            c = (c.multiply(BigInteger.valueOf(base))).mod(modulus);
        }
        return c;

         */
    }

    public static long modular_pow(long base, long exponent, long modulus) {
        if (modulus == 1) {
            return 0;
        }
        long c = 1;
        for (long e_prime = 0; e_prime < exponent; e_prime++) {
            c = (c * base) % modulus;
        }
        return c;
    }


    private static long RabinMillerTest(long n) {


        if (n % 2 == 0) {
            System.out.println("Composite.");
            return 0;

        } else {
            long u = n - 1;
            int t = 0;
            while (u % 2 == 0) {
                u = u / 2;
                t++;
            }
            System.out.println("t = " + Integer.toString(t));
            System.out.println("u = " + Long.toString(u));
            for (int j = 0; j < 20; j++) {
                long a = ThreadLocalRandom.current().nextLong(n - 1) + 1;

                if (modular_pow(a, n - 1, n) != 1) {
                    System.out.println("Composite by Fermat. Witness = " + Long.toString(a));
                    return a;
                }



                //System.out.println(a);
                long mod1, mod2;
                for (int i = 1; i <= t; i++) {
                    mod1 = modular_pow(a, (long) Math.pow(2, i) * u, n);
                    mod2 = modular_pow(a, (long) Math.pow(2, i - 1) * u, n);
                    long xtra_remainder = n + -1;
                    //System.out.println(mod1);
                    //System.out.println(mod2);
                    if (mod1 == 1) {
                        if (!((mod2 == 1) || (mod2 == xtra_remainder))) {
                            System.out.println("Composite by Rabin-Miller. Witness = " + Long.toString(a));
                            return a;
                        }
                    }
                }
            }


            return 0;
        }
    }


}

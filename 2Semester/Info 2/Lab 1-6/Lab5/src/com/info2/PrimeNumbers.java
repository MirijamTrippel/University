package com.info2;

import java.math.BigInteger;
import java.util.Random;

public class PrimeNumbers {

    public static void main(String[] args) {
        // write your code here
        //    BigInteger maxVal = 1099511627775;
        int maxVal = (int)Math.pow(2,39);;
        int smallBit = (int)Math.pow(2,19);

        System.out.println(log2(maxVal));
        System.out.println(log2(smallBit));
        long startTime = System.nanoTime();

        for (int i = 0; i<100; i++){
            boolean isPrime1 = isPrimeOptimized(smallBit);
        }

        long endTime = System.nanoTime();

        // get difference of two nanoTime values
        long timeElapsed = endTime - startTime;

        System.out.println("Total Execution time in nanoseconds  : " + timeElapsed);

        System.out.println("Total Execution time in milliseconds : " +
                timeElapsed / 1000000);

        System.out.println("Average Execution time in nanoseconds  : " + timeElapsed/100);

        System.out.println("Total Execution time in milliseconds : " +
                timeElapsed/100 / 1000000);

        startTime = System.nanoTime();


        for (int i = 0; i<100; i++){
            boolean isPrime2 = isPrimeOptimized(maxVal);
        }

        endTime = System.nanoTime();

        // get difference of two nanoTime values
        timeElapsed = endTime - startTime;
        System.out.println("Total Execution time in nanoseconds  : " + timeElapsed);

        System.out.println("Total Execution time in milliseconds : " +
                timeElapsed / 1000000);

        System.out.println("Average Execution time in nanoseconds  : " + timeElapsed/100);

        System.out.println("Total Execution time in milliseconds : " +
                timeElapsed/100 / 1000000);


    }

    static int log2(int value) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(value);
    }
    static int bits(int n){
        return   (int)(Math.log(n)/Math.log(2.0));
    }

    static int bitCount (int value){

        int count = 0;
        while (value > 0) {
            count++;
            value = value >> 1;
        }
        return count;
    }


    static boolean isPrime(int n) {
        // Corner case
        if (n <= 1) return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }


    public static boolean isPrimeOptimized(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;

        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }

    /* Iterative Function to calculate
   // (a^n)%p */
    static long fermat(long a, long n, long p) {

        long res = 1;
        for (int i = 0; i < n; i++) {
            res *= a;
            res %= p;
        }
        return res % p;

    }

    static boolean isPrimeFermat(int n, int k) {
        // base case
        if (n == 0 || n == 1)
            return false;
        // base case - 2 is prime
        if (n == 2)
            return true;
        // an even number other than 2 is composite
        if (n % 2 == 0)
            return false;

        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            long r = Math.abs(rand.nextLong());
            // make sure it is in range of (1<a<n-1)
            long a = r % (n - 1) + 1;
            if (fermat(a, n - 1, n) != 1)
                return false;
        }
        return true;
    }


}

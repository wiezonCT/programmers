package org.example;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        solution();
    }

    public static void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        makePrimeList(b);
        System.out.print(calculateAlmostPrime(a, b));
    }

    private static void makePrimeList(long b) {
        isPrime = new boolean[(int) (Math.sqrt(b) + 1)];
        Arrays.fill(isPrime, true);

        for (int i = 2; i * i <= Math.sqrt(b); i++) {
            if (!isPrime[i]) {
                continue;
            }

            for (int j = i + i; j <= Math.sqrt(b); j += i) {
                isPrime[j] = false;
            }
        }
    }

    private static int calculateAlmostPrime(long a, long b) {
        int cnt = 0;

        for (int i = 2; i < isPrime.length; i += 1) {
            if (!isPrime[i]) {
                continue;
            }

            long tmp = i;

            while (tmp <= 1.0 * b / i) {
                if (tmp >= 1.0 * a / i) {
                    cnt++;
                }

                tmp *= i;
            }
        }

        return cnt;
    }
}
package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder primeList = new StringBuilder();

    public static void main(String[] args) throws IOException {
        solution();
    }

    public static void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        makePrimeList(a, b);
        System.out.print(primeList.toString());
    }

    private static void makePrimeList(int a, int b) {
        boolean[] isPrime = new boolean[b + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i <= b; i++) {
            if (!isPrime[i]) {
                continue;
            }

            if (a <= i) {
                primeList.append(i).append("\n");
            }

            for (int j = i + i; j <= b; j += i) {
                isPrime[j] = false;
            }
        }
    }

}
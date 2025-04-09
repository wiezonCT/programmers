package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int SIZE = 1_000_000;

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer> primeList = new ArrayList<>();
    static StringTokenizer st;
    static long min, max;

    public static void main(String[] args) throws IOException {
        solution();
    }

    public static void solution() throws IOException {
        init();
        makePrimeList();
        System.out.print(countNotSquare());
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());
    }

    private static void makePrimeList() {
        boolean[] isPrime = new boolean[SIZE];
        Arrays.fill(isPrime, true);

        for (int i = 2; i < SIZE; i++) {
            if (!isPrime[i]) {
                continue;
            }

            primeList.add(i);

            for (int j = i + i; j < SIZE; j += i) {
                isPrime[j] = false;
            }
        }

    }

    private static int countNotSquare() {
        int cnt = 0;
        int size = (int) (max - min);
        boolean[] isNotSquare = new boolean[size + 1];
        Arrays.fill(isNotSquare, true);

        for (int prime : primeList) {
            long square = (long) prime * prime;
            if (square > max) {
                break;
            }

            long start = min % square == 0 ? 0 : min + square - min % square - min;

            while (start <= size) {
                isNotSquare[(int) start] = false;
                start += square;
            }
        }

        for (boolean b : isNotSquare) {
            if (b) {
                cnt++;
            }
        }

        return cnt;
    }
}
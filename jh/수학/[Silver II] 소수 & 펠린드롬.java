package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer> primeList = new ArrayList<>();
    static final int SIZE = 1_003_001;

    public static void main(String[] args) throws IOException {
        solution();
    }

    public static void solution() throws IOException {
        int n = Integer.parseInt(br.readLine());
        makePrimeList();
        System.out.print(findPalindrome(n));
    }

    private static void makePrimeList() {
        boolean[] isPrime = new boolean[SIZE + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i <= SIZE; i++) {
            if (!isPrime[i]) {
                continue;
            }

            primeList.add(i);

            for (int j = i + i; j <= SIZE; j += i) {
                isPrime[j] = false;
            }
        }
    }

    private static int findPalindrome(int n) {
        int l, r;
        String primeString;
        boolean isPalindrome;

        for (int prime : primeList) {
            primeString = String.valueOf(prime);
            l = 0;
            r = primeString.length() - 1;
            isPalindrome = true;

            while (l <= r) {
                if (primeString.charAt(l) != primeString.charAt(r)) {
                    isPalindrome = false;
                    break;
                }

                l += 1;
                r -= 1;
            }

            if (isPalindrome && prime >= n) {
                return prime;
            }
        }

        return -1;
    }
}
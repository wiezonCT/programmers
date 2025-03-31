package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        solution();
    }

    public static void solution() throws IOException {
        String s = br.readLine();
        int idx = calculatePlusAndGetMinusIndex(s);
        calculateMinus(s, idx);
        System.out.print(answer);
    }

    private static int calculatePlusAndGetMinusIndex(String s) {
        StringBuilder tmp = new StringBuilder();

        int minusIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                answer += Integer.parseInt(tmp.toString());
                tmp = new StringBuilder();
            }

            if (s.charAt(i) == '-') {
                answer += Integer.parseInt(tmp.toString());
                minusIndex = i;
                break;
            }

            tmp.append(s.charAt(i));
        }

        if (minusIndex == 0) {
            answer += Integer.parseInt(tmp.toString());
        }

        return minusIndex;
    }

    private static void calculateMinus(String s, int idx) {
        if (idx == 0) {
            return;
        }

        StringBuilder tmp = new StringBuilder();

        for (int i = idx + 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                answer -= Integer.parseInt(tmp.toString());
                tmp = new StringBuilder();
                continue;
            }

            tmp.append(s.charAt(i));
        }

        answer -= Integer.parseInt(tmp.toString());
    }
}
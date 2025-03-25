package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static List<Integer> minus = new ArrayList<>();
    static List<Integer> plus = new ArrayList<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        solution();
    }

    public static void solution() throws IOException {
        init();

    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp <= 0) {
                minus.add(tmp);
                continue;
            }

            plus.add(tmp);
        }

        minus.sort(Integer::compareTo);
        plus.sort(Integer::compareTo);

        plusCalculate();
        minusCalculate();
        System.out.print(answer);
    }

    private static void plusCalculate() {
        int size = plus.size();

        for (int i = size - 1; i >= 0; i--) {
            int cur = plus.get(i);
            int tmp = -1;
            if (i != 0) {
                tmp = plus.get(i - 1);
            }

            if (tmp != -1 && tmp != 1) {
                i--;
                answer += cur * tmp;
                continue;
            }

            answer += cur;
        }
    }

    private static void minusCalculate() {
        int size = minus.size();

        for (int i = 0; i < size; i++) {
            int cur = minus.get(i);
            int tmp = 1;

            if (i != size - 1) {
                tmp = minus.get(i + 1);
            }

            if (tmp != 1) {
                i++;
                answer += cur * tmp;
                continue;
            }

            answer += cur;
        }
    }
}
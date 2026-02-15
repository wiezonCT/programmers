import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Problem: 문자열 교환
 * Number: 1522
 * Tier: Silver
 * Link: https://www.acmicpc.net/problem/1522
 *
 * @author 이종현
 * @date 2026-02-09
 * @category 그리디
 */
public class 문자열교환_1522 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static String input;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(solve());
    }

    private static void init() throws IOException {
        input = br.readLine();
    }

    private static int solve() {
        int len = input.length();
        int aCount = 0;

        for (int i = 0; i < len; i++) {
            if (input.charAt(i) == 'a') {
                aCount++;
            }
        }

        if (aCount == 0 || aCount == 1) {
            return 0;
        }

        input += input;

        int aCurrent = 0;

        for (int i = 0; i < aCount; i++) {
            if (input.charAt(i) == 'a') {
                aCurrent++;
            }
        }

        int aMax = aCurrent;

        for (int i = 1; i < len; i++) {
            if (input.charAt(i - 1) == 'a') {
                aCurrent--;
            }

            if (input.charAt(i + aCount - 1) == 'a') {
                aCurrent++;
            }

            aMax = Math.max(aMax, aCurrent);
        }

        return aCount - aMax;
    }
}
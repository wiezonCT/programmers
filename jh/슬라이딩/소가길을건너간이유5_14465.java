import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem: 소가 길을 건너간 이유 5
 * Number: 14465
 * Tier: Silver
 * Link: https://www.acmicpc.net/problem/14465
 *
 * @author 이종현
 * @date 2026-02-14
 * @category 슬라이딩
 * @description
 *
 */
public class 소가길을건너간이유5_14465 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    static int n, k, b;
    static int[] crashes;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(solve());
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        crashes = new int[n + 1];

        for (int i = 0; i < b; i++) {
            crashes[Integer.parseInt(br.readLine())] = 1; // 1 고장
        }
    }

    private static int solve() {
        int crashCount = 0;

        // 처음 한 사이클
        for (int i = 1; i <= k; i++) {
            if (crashes[i] == 1) {
                crashCount++;
            }
        }

        int answer = crashCount;

        for (int i = k + 1; i <= n; i++) {
            if (crashes[i - k] == 1) {
                crashCount--;
            }

            if (crashes[i] == 1) {
                crashCount++;
            }

            answer = Math.min(answer, crashCount);
        }

        return answer;
    }
}
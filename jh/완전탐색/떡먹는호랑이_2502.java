import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem: 떡 먹는 호랑이
 * Number: 2502
 * Tier: Silver
 * Link: https://www.acmicpc.net/problem/2502
 *
 * @author 이종현
 * @date 2026-02-20
 * @category dp, 완탐
 * @description 떡최몇
 *
 */
public class 떡먹는호랑이_2502 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    static int d, k;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
    }

    private static void solve() {
        StringBuilder sb = new StringBuilder();

        Node[] rices = new Node[d + 1];
        rices[1] = new Node(1, 0);
        rices[2] = new Node(0, 1);

        for (int i = 3; i <= d; i++) {
            rices[i] = new Node(rices[i - 2].a + rices[i - 1].a, rices[i - 2].b + rices[i - 1].b);
        }

        // a 떡 개수 임의 대입
        for (int i = 1; i <= k; i++) {
            int a = k - i * rices[d].a;

            // a 대입해서 남은 떡을 b 계수로 나눴을 때 나누어 떨어지면
            if (a % rices[d].b == 0) {
                sb.append(i).append("\n").append(a / rices[d].b);
                System.out.print(sb);
                return;
            }
        }
    }

    // a는 첫 째날 떡, b는 둘 째날 떡 수
    static class Node {
        int a, b;

        Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}

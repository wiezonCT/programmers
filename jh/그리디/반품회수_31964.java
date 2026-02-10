import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem: 반품 회수
 * Number: 31964
 * Tier: ?
 * Link: https://www.acmicpc.net/problem/31964
 *
 * @author 이종현
 * @date 2026-02-10
 * @category BFS
 * @description 가장 먼 곳으로 가서 그냥 오면 되지 않나?
 *
 */
public class 반품회수_31964 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    static int n;
    static List<Integer> positions = new ArrayList<>();
    static List<Integer> times = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(solve());
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            positions.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            times.add(Integer.parseInt(st.nextToken()));
        }
    }

    private static int solve() {
        // 끝에꺼 먼저 들리고..
        int currentPosition = positions.get(n - 1);
        int currentTime = Math.max(times.get(n - 1), currentPosition);

        System.out.println(currentTime);
        for (int i = n - 2; i >= 0; i--) {
            int t = times.get(i);
            int pos = positions.get(i);
            int diffPos = currentPosition - pos;
            currentPosition = pos;

            currentTime += diffPos;

            if (currentTime >= t) {
                continue;
            }

            currentTime = t;
        }

        return currentTime + positions.getFirst();
    }
}

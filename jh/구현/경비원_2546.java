import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

/**
 * Problem: 경비원
 * Number: 2564
 * Tier: Silver
 * Link: https://www.acmicpc.net/problem/2564
 *
 * @author 이종현
 * @date 2026-02-12
 * @category 구현
 * @description
 *
 */
public class 경비원_2546 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    static int width, height, round, n, xPos;

    static int[] map;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(solve());
    }

    public static int solve() {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            answer += searchMin(map[i]);
        }

        return answer;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());
        round = (width + height) * 2;

        n = Integer.parseInt(br.readLine());

        map = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = converter(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        xPos = converter(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    /**
     *  입력 1: 북, 2: 남, 3: 서, 4: 동
     *  변환 1: 남, 2: 서, 3: 동, 4: 북
     */
    private static int converter(int cp, int pos) {
        return switch (cp) {
            case 1 -> width + height + pos;
            case 2 -> width - pos;
            case 3 -> width + height - pos;
            case 4 -> width + height + width + pos;
            default -> -1;
        };
    }

    /**
     * 시계방향, 반시계방향 비교
     */
    private static int searchMin(int targetPos) {
        int dist = Math.abs(targetPos - xPos);

        return Math.min(dist, round - dist);
    }
}

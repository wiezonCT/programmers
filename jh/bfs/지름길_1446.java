import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Problem: 지름길
 * Number: 1446
 * Tier: Silver
 * Link: https://www.acmicpc.net/problem/1446
 *
 * @author 이종현
 * @date 2026-02-20
 * @category bfs
 * @description 생각난게 bfs였는데, dp나 다익스트라로 푸는 문제였나봄....
 *
 */
public class 지름길_1446 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    static int n, d, answer;
    static List<ShortCut> shortCuts = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        bfs();
        System.out.print(answer);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        answer = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if (end - start < dist) {
                continue;
            }

            if (end > d) {
                continue;
            }

            shortCuts.add(new ShortCut(start, end, dist));
        }
    }

    private static void bfs() {
        Deque<Car> queue = new ArrayDeque<>();
        queue.add(new Car(0, 0));

        Collections.sort(shortCuts);

        while (!queue.isEmpty()) {
            Car car = queue.poll();

            if (car.point == d) {
                answer = Math.min(answer, car.dist);
                continue;
            }

            if (car.point > d) {
                continue;
            }

            for (ShortCut shortCut : shortCuts) {
                int start = shortCut.start;
                int end = shortCut.end;
                int dist = shortCut.dist;

                if (start > car.point) {
                    break;
                }

                if (start == car.point) {
                    queue.add(new Car(end, car.dist + dist));
                }
            }

            queue.add(new Car(car.point + 1, car.dist + 1));
        }
    }

    static class ShortCut implements Comparable<ShortCut> {
        int start, end, dist;

        ShortCut(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(ShortCut o) {
            return this.start - o.start;
        }
    }

    static class Car {
        int point, dist;

        Car(int point, int dist) {
            this.point = point;
            this.dist = dist;
        }
    }
}

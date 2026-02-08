import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Problem: 그래프 탐색 2
 * Number: 14218
 * Tier:
 * Link: https://www.acmicpc.net/problem/14218
 *
 * @author 이종현
 * @date 2026-02-08
 * @category BFS
 */
public class Graph_14218 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();

    private static int n, m, q;
    static List<List<Integer>> map = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            int[] dist = bfs(1);

            Arrays.stream(dist, 1, n + 1).forEach(x -> sb.append(x).append(" "));
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        q = Integer.parseInt(br.readLine());
    }

    private static void addEdge(int u, int v) {
        map.get(u).add(v);
        map.get(v).add(u);
    }

    private static int[] bfs(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : map.get(cur)) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    queue.offer(next);
                }
            }
        }

        return dist;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Problem: 폴짝폴짝
 * Number: 1326
 * Tier: Silver
 * Link: https://www.acmicpc.net/problem/1326
 *
 * @author 이종현
 * @date 2026-02-11
 * @category bfs, Graph
 * @description
 *
 */
public class 폴짝폴짝_1326 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    static int n, a, b;
    static List<Integer> lines =  new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(bfs());
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            lines.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
    }

    private static int bfs() {
        Deque<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        q.add(new Node(a - 1, 0));
        visited[a - 1] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.pos == b - 1) {
                return cur.time;
            }

            int power = lines.get(cur.pos);

            for (int next = cur.pos + power; next < n; next += power) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new Node(next, cur.time + 1));
                }
            }

            for (int next = cur.pos - power; next >= 0; next -= power) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new Node(next, cur.time + 1));
                }
            }
        }

        return -1;
    }

    static class Node {
        int pos, time;

        Node(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }
}

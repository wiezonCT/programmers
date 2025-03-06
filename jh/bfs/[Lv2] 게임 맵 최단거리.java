import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public static void main(String[] args) {
        System.out.print(solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}}));
    }

    private static int solution(int[][] maps) {
        return bfs(maps);
    }

    private static int bfs(int[][] maps)  {
        int[] d = new int[]{0, 0, -1, 1};
        int y = maps.length;
        int x = maps[0].length;

        boolean[][] visited = new boolean[y][x];
        visited[0][0] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, maps[0][0]));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.y == y - 1 && cur.x == x - 1) {
                return cur.cost;
            }

            for (int i = 0; i < 4; i++) {
                int dy = cur.y + d[i];
                int dx = cur.x + d[3 - i];

                if (dy < 0 || dy >= y) {
                    continue;
                }

                if (dx < 0 || dx >= x) {
                    continue;
                }

                if (!visited[dy][dx] && maps[dy][dx] == 1) {
                    q.add(new Node(dy, dx, cur.cost + maps[dy][dx]));
                    visited[dy][dx] = true;
                }
            }
        }

        return -1;
    }

    static class Node {
        int y, x, cost;

        Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }

        void print() {
            System.out.println(y + " " + x + " " + cost);
        }
    }
}
package org.example;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    static int[][] pic;
    static boolean[][] visited;
    static int y, x;
    static int[] d = new int[]{0, 0, -1, 1};

    public static void main(String[] args) {
        System.out.print(Arrays.toString(solution(6, 4, new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}})));
    }

    public static int[] solution(int m, int n, int[][] picture) {
        pic = picture;
        y = m;
        x = n;
        visited = new boolean[m][n];

        int count = 0;
        int max = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    visited[i][j] = true;
                    max = Math.max(bfs(new Node(i, j, picture[i][j])), max);
                    count++;
                }
            }
        }

        return new int[]{count, max};
    }

    private static int bfs(Node node) {
        Queue<Node> q = new LinkedList<>();
        int cnt = 1;
        q.add(node);

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int dy = cur.y + d[i];
                int dx = cur.x + d[3 - i];

                if (dy >= y || dy < 0) {
                    continue;
                }

                if (dx >= x || dx < 0) {
                    continue;
                }

                if (visited[dy][dx] || pic[dy][dx] != cur.value) {
                    continue;
                }

                visited[dy][dx] = true;
                q.add(new Node(dy, dx, cur.value));
                cnt++;
            }
        }

        return cnt;
    }

    static class Node {
        int y, x, value;

        Node(int y, int x, int value) {
            this.y = y;
            this.x = x;
            this.value = value;
        }
    }
}
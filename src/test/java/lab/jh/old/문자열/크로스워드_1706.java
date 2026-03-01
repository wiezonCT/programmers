package lab.jh.old.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem: 크로스워드
 * Number: 1706
 * Tier: Silver
 * Link: https://www.acmicpc.net/problem/1706
 *
 * @author 이종현
 * @date 2026-02-26
 * @category 문자열
 * @description
 *
 */
public class 크로스워드_1706 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    static int n, m;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        String answer = "zzzzzzzzzzzzzzzzzzzzz";
        answer = checkHeight(answer);
        answer = checkWidth(answer);

        System.out.print(answer);
    }

    private static String checkWidth(String min) {
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < m; j++) {
                if (map[i][j] == '#') {
                    if (sb.length() >= 2 && sb.toString().compareTo(min) < 0) {
                        min = sb.toString();
                    }
                    sb.setLength(0);
                } else {
                    sb.append(map[i][j]);
                }
            }

            if (sb.length() >= 2 && sb.toString().compareTo(min) < 0) {
                min = sb.toString();
            }
        }
        return min;
    }

    private static String checkHeight(String min) {
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < n; j++) {
                if (map[j][i] == '#') {
                    if (sb.length() >= 2 && sb.toString().compareTo(min) < 0) {
                        min = sb.toString();
                    }
                    sb.setLength(0);
                } else {
                    sb.append(map[j][i]);
                }
            }

            if (sb.length() >= 2 && sb.toString().compareTo(min) < 0) {
                min = sb.toString();
            }
        }
        return min;
    }


    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
            }
        }
    }
}

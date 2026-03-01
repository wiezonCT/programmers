package lab.jh.old.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Problem: 접두사 찾기
 * Number: 14426
 * Tier: Silver
 * Link: https://www.acmicpc.net/problem/14426
 *
 * @author 이종현
 * @date 2026-02-25
 * @category 이분 탐색, 문자열
 * @description
 *
 */
public class 접두사찾기_14426 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    static int n, m;
    static String[] parts;

    static Map<Character, List<String>> totals = new HashMap<>();

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int answer = 0;

        for (String part : parts) {
            answer += binarySearch(part);
        }

        System.out.print(answer);
    }

    private static int binarySearch(String part) {
        List<String> total = totals.get(part.charAt(0));
        int len = total.size();
        int left = 0, right = len - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (total.get(mid).startsWith(part)) {
                return 1;
            }

            if (total.get(mid).compareTo(part) < 0) {
                left = mid + 1;
                continue;
            }

            right = mid - 1;
        }

        return 0;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parts = new String[m];

        for (int i = 97; i <= 122; i++) {
            totals.put((char) i, new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            totals.get(line.charAt(0)).add(line);
        }

        for (int i = 0; i < m; i++) {
            parts[i] = br.readLine();
        }

        for (int i = 97; i <= 122; i++) {
            Collections.sort(totals.get((char) i));
        }
    }
}

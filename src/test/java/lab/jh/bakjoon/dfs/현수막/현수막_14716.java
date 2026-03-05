package lab.jh.bakjoon.dfs.현수막;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/**
 * Problem: 현수막
 * Number: 14716
 * Tier: Silver
 * Link: https://www.acmicpc.net/problem/14716
 *
 * @author 이종현
 * @date 2026-03-05
 * @category DFS
 * @memory 25517 MB
 * @time 200 ms
 * @description
 *
 */
public class 현수막_14716 {

    private static BufferedReader br;
    private static StringTokenizer st;

    static int m, n;
    static int[][] map;
    static boolean[][] visited;

    static int[] dy = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dx = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(solve());
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int solve() {
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    dfs(i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private static void dfs(int y, int x) {
        visited[y][x] = true;

        for (int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= m || ny < 0 || nx >= n || nx < 0) continue;

            if (!visited[ny][nx] && map[ny][nx] == 1) {
                dfs(ny, nx);
            }
        }
    }

    // ==========================================================
    // 여기서부터는 로컬 테스트용 코드
    // ==========================================================
    private ByteArrayOutputStream outputStreamCaptor;
    private final PrintStream standardOut = System.out;

    @BeforeEach
    public void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(
                        "8 19\n" +
                                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                                "0 0 0 1 0 0 0 1 0 0 0 1 0 1 1 1 1 1 0\n" +
                                "0 0 1 0 1 0 0 1 1 0 0 1 0 0 0 1 0 0 0\n" +
                                "0 1 0 0 0 1 0 1 0 1 0 1 0 0 0 1 0 0 0\n" +
                                "0 1 1 1 1 1 0 1 0 1 0 1 0 0 0 1 0 0 0\n" +
                                "0 1 0 0 0 1 0 1 0 0 1 1 0 0 0 1 0 0 0\n" +
                                "0 1 0 0 0 1 0 1 0 0 0 1 0 0 0 1 0 0 0\n" +
                                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0",
                        "3"
                )
        );
    }

    @ParameterizedTest(name = "{index}번째 예제 테스트")
    @MethodSource("provideTestCases")
    void test(String input, String expectedOutput) throws Exception {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        현수막_14716.main(new String[]{});

        Assertions.assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }
}

package lab.jh.bakjoon.구현.영재의_시험;

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
 * Problem: 영재의 시험
 * Number: 19949
 * Tier: Silver
 * Link: https://www.acmicpc.net/problem/19949
 *
 * @author 이종현
 * @date 2026-03-03
 * @category DFS
 * @memory 196960 MB
 * @time 276 ms
 * @description
 */
public class 영재의_시험_19949 {

    private static BufferedReader br;
    private static StringTokenizer st;

    static int answer;
    static int[] answers;

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, 0, new Select(-1, -1));
        System.out.print(answer);
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        answer = 0;
        answers = new int[10];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            answers[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void dfs(int depth, int point, Select previous) {
        if (depth == 10) {
            if (point >= 5) {
                answer++;
            }

            return;
        }

        for (int i = 1; i <= 5; i++) {
            if (i == previous.select) {
                if (previous.count == 2) {
                    continue;
                }

                previous.count++;
                if (i == answers[depth]) {
                    dfs(depth + 1, point + 1, previous);
                    continue;
                }

                dfs(depth + 1, point, previous);
                continue;
            }

            if (i == answers[depth]) {
                dfs(depth + 1, point + 1, new Select(i, 1));
                continue;
            }

            dfs(depth + 1, point, new Select(i, 1));
        }
    }

    static class Select {
        int select, count;

        public Select(int select, int count) {
            this.select = select;
            this.count = count;
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
                        "1 2 3 4 5 1 2 3 4 5",
                        "261622"
                )
        );
    }

    @ParameterizedTest(name = "{index}번째 예제 테스트")
    @MethodSource("provideTestCases")
    void test(String input, String expectedOutput) throws Exception {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        영재의_시험_19949.main(new String[]{});

        Assertions.assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }
}

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
 * @memory 14332 MB
 * @time 120 ms
 * @description 메모리 90%이상, time 50% 이상 절약.. class 남용 및 백트래킹 고려해보기
 */
public class Refactor_영재의_시험_19949 {

    private static BufferedReader br;
    private static StringTokenizer st;

    static int answer;
    static int[] answers;

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, 0, 0, 0);
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

    // 개선 포인트 1. class 객체 대신 기본형인 int를 각각 넘겨 메모리 절약
    private static void dfs(int depth, int point, int previousChoice, int consecutiveCount) {
        // 개선 포인트 2. 남은 depth를 다 돌아도 5점을 넘을 수 없다면 return 처리 (백트래킹)
        if (point + (10 - depth) < 5) {
            return;
        }

        if (depth == 10) {
            if (point >= 5) {
                answer++;
            }
            return;
        }

        for (int i = 1; i <= 5; i++) {
            if (previousChoice == i && consecutiveCount == 2) {
                continue;
            }

            int nextPoint = point + (i == answers[depth] ? 1 : 0);
            int nextConsecutive = (previousChoice == i ? consecutiveCount + 1 : 1);

            dfs(depth + 1, nextPoint, i, nextConsecutive);
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

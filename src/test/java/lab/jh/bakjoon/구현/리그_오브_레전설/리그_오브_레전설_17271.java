package lab.jh.bakjoon.구현.리그_오브_레전설;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/**
 * Problem: 리그 오브 레전설
 * Number: 17271
 * Tier: Silver
 * Link: https://www.acmicpc.net/problem/17271
 *
 * @author 이종현
 * @date 2026-03-07
 * @category 구현
 * @memory 14220 KB
 * @time 104 ms
 * @description
 *
 */
public class 리그_오브_레전설_17271 {

    private static BufferedReader br;
    private static StringTokenizer st;

    static int n, m;
    static int[] dp;

    private static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n + 1];
        Arrays.fill(dp, 1);
    }

    private static void solve() {
        for (int i = m; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - m]) % MOD;
        }

        System.out.print(dp[n]);
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
                        "4 2",
                        "5"
                ),
                Arguments.of(
                        "3 2",
                        "3"
                ),
                Arguments.of(
                        "250 2",
                        "841382049"
                )
        );
    }

    @ParameterizedTest(name = "{index}번째 예제 테스트")
    @MethodSource("provideTestCases")
    void test(String input, String expectedOutput) throws Exception {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // 1. 측정 전 가비지 컬렉터(GC) 실행 (최대한 찌꺼기 메모리 정리)
        Runtime.getRuntime().gc();

        // 2. 시작 전 메모리 및 시간 기록
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long startTime = System.currentTimeMillis();

        리그_오브_레전설_17271.main(new String[]{});

        // 3. 종료 후 메모리 및 시간 기록
        long endTime = System.currentTimeMillis();
        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // 4. 결과 계산 (ms, KB 단위로 변환)
        long timeElapsed = endTime - startTime;
        long memoryUsed = (afterMemory - beforeMemory) / 1024; // Byte -> KB

        System.err.printf("[측정 결과] 시간: %d ms, 메모리: %d KB%n", timeElapsed, memoryUsed);

        Assertions.assertEquals(
                expectedOutput.trim(),
                outputStreamCaptor.toString().trim().replace("\r\n", "\n")
        );
    }
}

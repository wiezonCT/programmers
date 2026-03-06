package lab.jh.bakjoon.구현.민겸_수;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Problem: 민겸 수
 * Number: 21314
 * Tier: Silver
 * Link: https://www.acmicpc.net/problem/21314
 *
 * @author 이종현
 * @date 2026-03-06
 * @category 구현
 * @memory 14944 KB
 * @time 128 ms
 * @description
 */
public class 민겸_수_21314 {

    private static BufferedReader br;

    static String mk;
    static int len;

    static final char M_CHAR = 'M';
    static final char K_CHAR = 'K';

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        mk = br.readLine();
        len = mk.length();
    }

    private static void solve() {
        StringBuilder sb = new StringBuilder();

        sb.append(mkConverter(getMaxMK())).append('\n').append(mkConverter(getMinMK()));

        System.out.print(sb);
    }

    private static List<String> getMaxMK() {
        List<String> mks = new ArrayList<>();
        StringBuilder cur = new StringBuilder();

        for (char c : mk.toCharArray()) {
            cur.append(c);

            if (c == K_CHAR) {
                mks.add(cur.toString());
                cur.setLength(0);
            }
        }
        
        if (!cur.isEmpty()) {
            for (int i = 0; i < cur.length(); i++) {
                mks.add(String.valueOf(M_CHAR));
            }
        }

        return mks;
    }

    private static List<String> getMinMK() {
        List<String> mks = new ArrayList<>();

        StringBuilder cur = new StringBuilder();

        for (char c : mk.toCharArray()) {
            if (c == K_CHAR) {
                if (!cur.isEmpty()) {
                    mks.add(cur.toString());
                }

                mks.add("" + c);
                cur = new StringBuilder();
                continue;
            }

            cur.append(c);
        }

        if (!cur.isEmpty()) {
            mks.add(cur.toString());
        }

        return mks;
    }

    private static String mkConverter(List<String> mks) {
        StringBuilder sb = new StringBuilder();

        for (String mk : mks) {
            int len = mk.length();

            if (mk.charAt(len - 1) == K_CHAR) {
                sb.append('5');
                sb.append("0".repeat(len - 1));
                continue;
            }
            sb.append('1');
            sb.append("0".repeat(len - 1));
        }

        return sb.toString();
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
                        "MKM",
                        "501\n" +
                                "151"
                ),
                Arguments.of(
                        "MKKMMK",
                        "505500\n" +
                                "155105"
                ),
                Arguments.of(
                        "MMMMMKMMKMMMMMMMMMMKMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
                        "50000050050000000000111111111111111111111111111111\n" +
                                "10000510510000000005100000000000000000000000000000"
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

        민겸_수_21314.main(new String[]{});

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

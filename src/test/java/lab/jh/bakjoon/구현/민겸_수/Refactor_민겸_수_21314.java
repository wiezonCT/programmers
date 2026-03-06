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
 * @memory 14336 KB
 * @time 108 ms
 * @description
 * 1. 굳이 함수 나누지 않기 -> 한 싸이클로 끝내면 효율 좋으니
 * 2. List<String> 없애고 int로 개수만 카운팅
 * 3. StringBuilder 반복생성 없애기
 *
 * 거의 실패? 큰 차이가 없음
 * 속도와 메모리에 집중한다면 이렇게 하는게 좋겠지만, 나눠서 보기좋게 하는게 좋지 않을까라는 생각? (큰 차이가 없다면)
 */
public class Refactor_민겸_수_21314 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String mk = br.readLine();

        System.out.print(getMax(mk) + "\n");
        System.out.print(getMin(mk));
    }

    private static String getMax(String s) {
        StringBuilder sb = new StringBuilder();
        int mCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'M') {
                mCount++;
            } else {
                sb.append('5');
                sb.append("0".repeat(mCount));
                mCount = 0;
            }
        }

        if (mCount > 0) {
            sb.append("1".repeat(mCount));
        }

        return sb.toString();
    }

    private static String getMin(String s) {
        StringBuilder sb = new StringBuilder();
        int mCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'M') {
                mCount++;
            } else { // 'K'를 만났을 때
                if (mCount > 0) {
                    sb.append('1');
                    sb.append("0".repeat(mCount - 1));
                    mCount = 0;
                }
                sb.append('5');
            }
        }

        // 끝에 남은 M 처리 (최솟값이므로 10...0 묶음 처리)
        if (mCount > 0) {
            sb.append('1');
            sb.append("0".repeat(mCount - 1));
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

        Refactor_민겸_수_21314.main(new String[]{});

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

package lab.jh.bakjoon.수열;

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
 * Problem: 민균이의 계략
 * Number: 11568
 * Tier: Silver
 * Link: https://www.acmicpc.net/problem/11568
 *
 * @author 이종현
 * @date 2026-03-02
 * @category 수열, dp
 * @description
 */
public class 민균이의_계략_11568 {

    private static BufferedReader br;
    private static StringTokenizer st;

    static int n;
    static int[] cards, lis;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        cards = new int[n];
        lis = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solve() {
        lis[0] = cards[0];
        int length = 1;

        for (int i = 1; i < n; i++) {
            int key = cards[i];

            if (lis[length - 1] < key) {
                lis[length] = key;
                length++;
                continue;
            }

            setKeyPosition(key, length);
        }

        System.out.print(length);
    }

    private static void setKeyPosition(int key, int length) {
        int left = 0, right = length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (lis[mid] < key) {
                left = mid + 1;
                continue;
            }

            right = mid;
        }

        lis[left] = key;
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
                        "5\n8 9 1 2 10",
                        "3"
                ),
                Arguments.of(
                        "8\n5 4 3 2 1 6 7 8",
                        "4"
                ),
                Arguments.of(
                        "1\n1",
                        "1"
                )
        );
    }

    @ParameterizedTest(name = "{index}번째 예제 테스트")
    @MethodSource("provideTestCases")
    void test(String input, String expectedOutput) throws Exception {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        민균이의_계략_11568.main(new String[]{});

        Assertions.assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }
}
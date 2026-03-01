package lab.jh.bakjoon.우선순위큐;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/**
 * Problem: 센티와 마법의 뿅망치
 * Number: 19638
 * Tier: Silver
 * Link: https://www.acmicpc.net/problem/19638
 *
 * @author 이종현
 * @date 2026-03-01
 * @category 우선순위큐
 * @description
 */
public class 센티와_마법의_뿅망치_19638 {

    private static BufferedReader br;
    private static StringTokenizer st;

    static int n, h, t;
    static PriorityQueue<Integer> giants;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        giants = new PriorityQueue<>(Collections.reverseOrder());
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            giants.add(Integer.parseInt(br.readLine()));
        }
    }

    private static void solve() {
        StringBuilder sb = new StringBuilder();
        int answer = 0;

        while (!giants.isEmpty() && answer < t) {
            int height = giants.peek();


            if (height < h || height == 1) {
                break;
            }

            giants.poll();
            giants.add(height / 2);

            answer++;
        }

        if (!giants.isEmpty() && giants.peek() < h) {
            sb.append("YES\n").append(answer);
        } else {
            sb.append("NO\n").append(giants.peek());
        }

        System.out.print(sb);
    }

    // ==========================================================
    // 여기서부터 로컬 테스트용 코드
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

    // 테케 넣기
    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(
                        "1 10 1\n20\n",
                        "NO\n10"
                ),
                Arguments.of(
                        "2 10 3\n16\n32\n",
                        "YES\n3"
                ),
                Arguments.of(
                        "2 10 3\n127\n8\n",
                        "NO\n15"
                ),
                Arguments.of(
                        "1 1 100000\n1\n",
                        "NO\n1"
                )
        );
    }

    @ParameterizedTest(name = "{index}번째 예제 테스트")
    @MethodSource("provideTestCases")
    void 백준_예제가_통과한다(String input, String expectedOutput) throws Exception {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        센티와_마법의_뿅망치_19638.main(new String[]{});

        Assertions.assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }
}

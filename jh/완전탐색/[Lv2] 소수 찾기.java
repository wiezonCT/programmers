import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {

    static Set<Integer> set = new HashSet<>();
    static String[] arr;
    static boolean[] isPrime;

    public static void main(String[] args) {
        System.out.print(solution("011"));
    }

    private static int solution(String numbers) {
        arr = numbers.split("");
        dfs(0, "", new boolean[numbers.length()]);
        makePrimeList((int) Math.pow(10, numbers.length()));
        int answer = 0;
        for (int tmp : set) {
            if (isPrime[tmp]) {
                answer++;
            }
        }
        return answer;
    }

    private static void dfs(int depth, String s, boolean[] visited) {

        if (depth == arr.length) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            String tmp = "";
            if (!visited[i]) {
                tmp += s + arr[i];
                set.add(Integer.parseInt(tmp));
                visited[i] = true;
                dfs(depth + 1, tmp, visited);
                visited[i] = false;
            }
        }
    }

    private static void makePrimeList(int n) {
        isPrime = new boolean[n + 1];

        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}
class Solution {

    static int[] nums;
    static int t, answer = 0, len;

    public static void main(String[] args) {
        System.out.print(solution(new int[]{1, 1, 1, 1, 1}, 3));
    }

    private static int solution(int[] numbers, int target) {
        nums = numbers;
        t = target;
        len = nums.length;
        dfs(0, 0);
        return answer;
    }

    private static void dfs(int depth, int cur) {
        if (depth == len) {
            if (cur == t) {
                answer++;
            }

            return;
        }

        dfs(depth + 1, cur + nums[depth]);
        dfs(depth + 1, cur - nums[depth]);
    }
}
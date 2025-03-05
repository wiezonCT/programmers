import java.util.Arrays;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] student = new int[n + 1];

        Arrays.fill(student, 1);

        for (int l : lost) {
            student[l]--;
        }

        for (int r : reserve) {
            student[r]++;
        }

        for (int i = 1; i <= n; i++) {
            if (student[i] == 0) {
                if (student[i - 1] == 2) {
                    student[i]++;
                    student[i - 1]--;
                    continue;
                }

                if (i != n && student[i + 1] == 2) {
                    student[i]++;
                    student[i + 1]--;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (student[i] != 0) {
                answer++;
            }
        }

        return answer;
    }
}
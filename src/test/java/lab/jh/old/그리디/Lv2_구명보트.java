package lab.jh.old.그리디;

import java.util.Arrays;

class Lv2_구명보트 {
    public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);

        int l = 0;
        int r = people.length - 1;

        while (l <= r) {
            if (people[l] + people[r] <= limit) {
                answer++;
                l++;
                r--;
                continue;
            }

            r--;
            answer++;
        }

        return answer;
    }
}
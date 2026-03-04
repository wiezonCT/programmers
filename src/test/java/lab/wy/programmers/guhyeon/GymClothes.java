package lab.wy.programmers.guhyeon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class GymClothes {
    @Test
    void test(){
        Assertions.assertEquals(5, solution(5, new int[]{2,4}, new int[]{1,3,5}));
        Assertions.assertEquals(4, solution(5, new int[]{2,4}, new int[]{3}));
        Assertions.assertEquals(2, solution(3, new int[]{3}, new int[]{1}));

        Assertions.assertEquals(5, bestPractice(5, new int[]{2,4}, new int[]{1,3,5}));
        Assertions.assertEquals(4, bestPractice(5, new int[]{2,4}, new int[]{3}));
        Assertions.assertEquals(2, bestPractice(3, new int[]{3}, new int[]{1}));
    }

    private Integer solution(int n, int[] lost, int[] reserved){
        int[] clothes = new int[n+1];
        for(int i = 1; i < n+1; i++){
            clothes[i] = 1;
        }

        for(int i = 0; i < lost.length; i++){
            clothes[lost[i]]--;
        }

        for(int i = 0; i < reserved.length; i++){
            clothes[reserved[i]]++;
        }

        for(int i = 1; i < n+1; i++){
            if(clothes[i] == 0){
                if(i != 1 && clothes[i-1] > 1){
                    clothes[i]++;
                    clothes[i-1]--;
                }else if(i != n && clothes[i+1] > 1){
                    clothes[i]++;
                    clothes[i+1]--;
                }
            }
        }




        return Arrays.stream(clothes).filter(count -> count >= 1).toArray().length;
    }

    public int bestPractice(int n, int[] lost, int[] reserve) {
        // 1. n+2 배열을 만들어서 양 끝(0번, n+1번)에 패딩(Padding)을 줍니다.
        // 이렇게 하면 i-1, i+1 접근 시 IndexOutOfBoundsException을 피할 수 있어 코드가 깔끔해집니다.
        int[] people = new int[n + 2];

        // 2. 기본값은 0 (체육복 1개 있음)으로 두고, 도난당하면 -1, 여벌이 있으면 +1을 해줍니다.
        // 이 방식의 장점: 도난당했는데 여벌이 있는 학생은 자동으로 0이 되어 따로 예외처리할 필요가 없습니다.
        for (int l : lost) {
            people[l]--;
        }
        for (int r : reserve) {
            people[r]++;
        }

        // 3. 정답의 최댓값은 전체 학생 수(n)로 두고, 못 빌린 학생만 빼는 식으로 계산합니다.
        int answer = n;

        for (int i = 1; i <= n; i++) {
            if (people[i] == -1) { // 체육복이 없는 학생이라면
                if (people[i - 1] == 1) { // 앞 번호 학생에게 빌림
                    people[i]++;
                    people[i - 1]--;
                } else if (people[i + 1] == 1) { // 뒷 번호 학생에게 빌림
                    people[i]++;
                    people[i + 1]--;
                } else { // 결국 못 빌림
                    answer--;
                }
            }
        }

        return answer;
    }
}

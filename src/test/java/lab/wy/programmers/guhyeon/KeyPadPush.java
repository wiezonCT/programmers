package lab.wy.programmers.guhyeon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class KeyPadPush {

    @Test
    void test(){
        Assertions.assertEquals("LRLLLRLLRRL", solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5},"right"));
        Assertions.assertEquals("LRLLRRLLLRR", solution(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2},"left"));
        Assertions.assertEquals("LLRLLRLLRL", solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0},"right"));

        Assertions.assertEquals("LRLLLRLLRRL", bestPractice(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5},"right"));
        Assertions.assertEquals("LRLLRRLLLRR", bestPractice(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2},"left"));
        Assertions.assertEquals("LLRLLRLLRL", bestPractice(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0},"right"));
    }


    // left(*), right(#)  위치 지정
    // 2차원 배열 >> 키패드 구현
    // 2,5,8,0 만 bfs() 왼쪽 / 오른쪽 기준 >> 최소값 (위치 최신화)
    // 1,4,7은 왼쪽 기록 / 3,6,9는 오른쪽 기록

    int[][] keypad = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}, {-1,0,-2}};
    private String solution(int[] keys, String hand) {
        Map<String, Integer> left = Map.of("x" ,3, "y", 0);
        Map<String, Integer> right = Map.of("x", 3, "y", 2);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < keys.length; i++) {
            // 왼손 우선 적용
            if(keys[i] == 1 || keys[i] == 4 || keys[i] == 7) {
                sb.append("L");
                left = findXY(keys[i]);
            }else if(keys[i] == 3 || keys[i] == 6 || keys[i] == 9) {
                sb.append("R");
                right = findXY(keys[i]);
            }else{
                String handUsed = minDistance(left, right, keys[i], hand);
                sb.append(handUsed);

                if(handUsed.equals("L")){
                    left = findXY(keys[i]);
                }else{
                    right = findXY(keys[i]);
                }
            }
        }

        return sb.toString();
    }

    public String bestPractice(int[] numbers, String hand) {

        StringBuilder answer = new StringBuilder();

        int left = 9;   // *
        int right = 11; // #

        for (int num : numbers) {

            int target = (num == 0) ? 10 : num - 1;

            // 왼쪽 라인
            if (target % 3 == 0) {
                answer.append("L");
                left = target;
            }
            // 오른쪽 라인
            else if (target % 3 == 2) {
                answer.append("R");
                right = target;
            }
            // 가운데
            else {

                int leftDist = manhetenDistance(left, target);
                int rightDist = manhetenDistance(right, target);

                if (leftDist < rightDist) {
                    answer.append("L");
                    left = target;
                } else if (rightDist < leftDist) {
                    answer.append("R");
                    right = target;
                } else {
                    if (hand.equals("left")) {
                        answer.append("L");
                        left = target;
                    } else {
                        answer.append("R");
                        right = target;
                    }
                }
            }
        }

        return answer.toString();
    }

    private Map<String, Integer> findXY(int key){
        for(int i = 0; i < keypad.length; i++){
            for(int j = 0; j < keypad[i].length; j++){
                if(keypad[i][j] == key){
                    return Map.of("x", i, "y", j);
                }
            }
        }
        return Map.of();
    }
    private String minDistance(Map<String, Integer> left, Map<String,Integer> right, int key, String hand){
        Map<String, Integer> target = findXY(key);
        int leftDistance = distance(left, target);
        int rightDistance = distance(right, target);

        if(leftDistance == rightDistance){
            if(hand.equals("left")){
                return "L";
            }else{
                return "R";
            }
        }else if (leftDistance < rightDistance){
            return "L";
        }else{
            return "R";
        }
    }
    private int distance(Map<String, Integer> from, Map<String, Integer> to){
        return Math.abs(from.get("x") - to.get("x"))
                + Math.abs(from.get("y") - to.get("y"));
    }
    // 멘헤튼 distance 거리 공식
    private int manhetenDistance(int from, int to) {
        return Math.abs(from / 3 - to / 3) + Math.abs(from % 3 - to % 3);
    }
}

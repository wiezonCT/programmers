import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        System.out.print(Arrays.toString(solution(10, 2)));
    }

    private static int[] solution(int brown, int yellow) {

        int width;
        for (int height = 1; height <= Math.sqrt(yellow); height++) {
            width = yellow / height;

            if (width * height != yellow) {
                continue;
            }

            if (width * 2 + height * 2 + 4 == brown) {
                return new int[]{width + 2, height + 2};
            }
        }

        return new int[]{};
    }
}
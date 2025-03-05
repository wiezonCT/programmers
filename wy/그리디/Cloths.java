import java.util.Arrays;

public class Cloths {
    public static void main(String[] args){
        int n = 5;
        int[] lost = {2,4};
        int[] reserve = {1,3,5};
        int result = n - lost.length;

        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if(lost[i] == -1) continue;
                if (reserve[j] == lost[i]) {
                    result++;
                    lost[i] = -1;
                    reserve[j] = -1;
                    break;
                }
            }
        }


        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if(reserve[j] == -1) continue;
                if (reserve[j] == lost[i] - 1 || reserve[j] == lost[i] + 1) {
                    result++;
                    reserve[j] = -1;
                    break;
                }
            }
        }

        System.out.println(result);
    }
}

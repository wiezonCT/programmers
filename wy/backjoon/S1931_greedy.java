package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S1931_greedy {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] arr = new int[size][2];

        for(int i = 0; i < size; i++){
            String[] arg = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(arg[0]);
            arr[i][1] = Integer.parseInt(arg[1]);
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int endTime = arr[0][1];
        int count = 1;
        for(int i = 1; i < size; i++){
            if(arr[i][0] >= endTime){
                count++;
                endTime = arr[i][1];
            }
        }


        System.out.println(count);
    }


}

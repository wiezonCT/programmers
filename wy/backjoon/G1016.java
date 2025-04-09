package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G1016 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        Long min = Long.parseLong(input[0]);
        Long max = Long.parseLong(input[1]);

        boolean[] check = new boolean[(int) (max - min +1)];

        for(long i = 2; i * i <= max; i++){
            long pow = i * i;
            long start_index = min / pow;
            if(min % pow != 0)
                start_index++;
            for(long j = start_index; pow * j <= max; j++){
                check[(int) ((j*pow) - min)] = true;
            }
        }
        int count = 0;
        for(int i = 0; i<= max - min; i++){
            if(!check[i]){
                count++;
            }
        }
        System.out.println(count);
    }
}

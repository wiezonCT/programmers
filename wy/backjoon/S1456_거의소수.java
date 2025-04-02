package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1456_거의소수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        Long Max = Long.parseLong(input[1]);
        Long Min = Long.parseLong(input[0]);
        long limit = (long)Math.sqrt(Max);
        Boolean[] sosu = new Boolean[(int) (limit + 1)];

        for (int i = 0; i <= limit ; i++) {
            if(i == 0 || i == 1){
                sosu[i] = false;
            }else{
                sosu[i] = true;
            }
        }


        for(int i = 2; i <= Math.sqrt(limit) ; i++){
            if(sosu[i]){
                for(int j = i*i;  j <= limit; j += i ){
                    sosu[j] = false;
                }
            }

        }


        int count = 0;
        for(int i =2; i <= limit; i++){
            if(sosu[i]){
                long first = (long)i*i;
                if(first >= Min && first <= Max){
                    count++;
                }
                for(long j = first*i; j <= Max; j *= i ){
                    if (j >= Min) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);

    }
}

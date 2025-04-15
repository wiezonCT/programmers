package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P_11689 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long num = Long.parseLong(br.readLine());
        long result = num;

        for(long i = 2; i < Math.sqrt(num); i++){
            if( num % i == 0){
                result = result - result/i;
                while(num % i == 0){
                    num /= i;
                }
            }

        }

        if( num > 1 ){
            result = result - result / num;
        }

        System.out.println(result);
    }
}

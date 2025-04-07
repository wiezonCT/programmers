package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G1747 {
    public static void main(String[] args) throws IOException {
        boolean[] sosu = new boolean[1_003_002];
        for (int i = 2; i < sosu.length; i++) {
            sosu[i] = true;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Integer start = Integer.parseInt(br.readLine());
        for(int i = 2; i < Math.sqrt(sosu.length); i++){
            if ( !sosu[i] )
                continue;
            for (int j = i*i ; j < sosu.length ; j += i) {
                sosu[j] = false;
            }
        }

        for (int i = start; i < sosu.length; i++) {

            if ( sosu[i] ){
                if ( reverse(i) == i ){
                    System.out.println(i);
                    break;
                }
            }
        }


    }

    public static Integer reverse(Integer number){
        Integer answer = 0;

        while( number != 0){
            answer = answer * 10 + number % 10;
            number /= 10;
        }

        return answer;
    }
}

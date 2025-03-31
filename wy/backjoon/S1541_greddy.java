package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1541_greddy {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String[] str = input.split("-");
        int first_sum = 0;
        int sum = 0;
        for (int i = 0; i< str.length; i++){
            if(i == 0){
                String[] str_temp = str[i].split("\\+");
                for (String s : str_temp) {
                    first_sum += Integer.parseInt(s);
                }
            }else{
                String[] str_temp = str[i].split("\\+");
                for (String s : str_temp) {
                    sum += Integer.parseInt(s);
                }
            }
        }

        System.out.println(first_sum - sum);
    }
}

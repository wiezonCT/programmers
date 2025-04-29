package backjoon.최소공배수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class S1934 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; i++){
            String[] array = br.readLine().split(" ");
            result.add(getResult(Integer.parseInt(array[0]), Integer.parseInt(array[1])));
        }

        for (Integer i : result) {
            System.out.println(i);
        }
    }

    public static Integer getResult(Integer left, Integer right){
        int leftIncrement = 1;
        int rightIncrement = 1;
        int compLeft = left;
        int compRight = right;
        while(compLeft != compRight){
            if(compLeft > compRight){
                compRight = right * ++rightIncrement;
            }else{
                compLeft = left * ++leftIncrement;
            }
        }
        return compLeft;
    }
}

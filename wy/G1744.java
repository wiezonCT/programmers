import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

public class G1744 {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> plusList = new ArrayList<>();
        List<Integer> minusList = new ArrayList<>();
        List<Integer> zeroList = new ArrayList<>();

        Integer size = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < size; i++){
            int temp = Integer.parseInt(bufferedReader.readLine());
            if(temp < 0 ){
                minusList.add(temp);
            }else if(temp == 0){
                zeroList.add(temp);
            }else{
                plusList.add(temp);
            }
        }

        Collections.sort(minusList);
        Collections.sort(plusList);

        int sum = 0;
        if(minusList.size()%2 ==0){
            for(int i = 0 ; i < minusList.size() -1; i = i + 2){
                sum = sum + (minusList.get(i) * minusList.get(i+1));
            }

            if (plusList.size() % 2 == 0){
                for(int i = plusList.size() -1; i >= 0; i = i -2 ){
                    if (plusList.get(i) == 1 || plusList.get(i-1) == 1){
                        sum = sum + plusList.get(i) + plusList.get(i-1);
                    }else{
                        sum = sum + (plusList.get(i) * plusList.get(i-1));
                    }
                }
            }else{
                for(int i = plusList.size() -1; i > 0; i = i -2 ){
                    if (plusList.get(i) == 1 || plusList.get(i-1) == 1){
                        sum = sum + plusList.get(i) + plusList.get(i-1);
                    }else{
                        sum = sum + (plusList.get(i) * plusList.get(i-1));
                    }
                }

                sum = sum + plusList.get(0);
            }

            System.out.println(sum);
        }else{
            if( minusList.size() == 1) {

            }else{
                for(int i = 0 ; i < minusList.size() -1; i = i + 2){
                    sum = sum + (minusList.get(i) * minusList.get(i+1));
                }
            }
            if(zeroList.isEmpty()){
               sum += minusList.get(minusList.size() - 1);
            }

            if(plusList.size() % 2 == 0){
                for(int i = plusList.size() -1; i >= 0; i = i -2 ){
                    if (plusList.get(i) == 1 || plusList.get(i-1) == 1){
                        sum = sum + plusList.get(i) + plusList.get(i-1);
                    }else{
                        sum = sum + (plusList.get(i) * plusList.get(i-1));
                    }
                }
            }else{
                for(int i = plusList.size() -1; i > 0; i = i -2 ){
                    if (plusList.get(i) == 1 || plusList.get(i-1) == 1){
                        sum = sum + plusList.get(i) + plusList.get(i-1);
                    }else{
                        sum = sum + (plusList.get(i) * plusList.get(i-1));
                    }
                }
                sum = sum + plusList.get(0);
            }



            System.out.println(sum);
        }

    }
}
package wy.완전탐색;

import java.util.*;



public class 소수찾기{

    public static void main(String[] args){

        String numbers= "17";
        Set<Integer> valueSet = new HashSet<>();
        int result = 0;

        generateValue(valueSet, "", numbers);    
        
        int maxValue = getMax(valueSet);
        boolean[] check = new boolean[maxValue + 1];
        Arrays.fill(check, true);
        check[0] = check[1] = false; // 0과 1은 소수가 아님

        for (int i = 2; i * i <= maxValue; i++) {
            if (check[i]) {
                for (int j = i * i; j <= maxValue; j += i) {
                    check[j] = false;
                }
            }
        }

        for (Integer value : valueSet) {
            if (check[value] == true){
                result++;
            }
        }

        System.out.println(result);
    }

    public static int getMax(Set<Integer> arr){
        int maxValue = 0;
        for (Integer integer : arr) {
            if(maxValue < integer){
                maxValue = integer;
            }
        }
        return maxValue;
    }

    public static void generateValue(Set<Integer> valueSet, String prefix, String remain ){
        if (!prefix.isEmpty()){
            valueSet.add(Integer.parseInt(prefix));
        }

         for (int i = 0; i < remain.length(); i++) {
            generateValue(valueSet, prefix + remain.charAt(i),
                                remain.substring(0, i) + remain.substring(i + 1));
        }
    }
    
}

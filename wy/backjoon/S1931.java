package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class S1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        List<Integer> startTime = new ArrayList<>();
        List<Integer> endTime = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++){
            String[] arr = br.readLine().split(" ");
            startTime.add(Integer.parseInt(arr[0]));
            endTime.add(Integer.parseInt(arr[1]));
        }
        DFS(0,-1, size, result, 0, startTime, endTime);
        Collections.sort(result);

        System.out.println(result.get(result.size()-1));


    }

    public static void DFS(int index, int compareIndex, int size, List<Integer> result, int count, List<Integer> startTime, List<Integer> endTime){
        if(index == size){
            result.add(count);
            return;
        }

        if (compareIndex == -1 || startTime.get(index) >= endTime.get(compareIndex)) {
            DFS(index + 1, index, size, result, count + 1, startTime, endTime);
        }

        DFS(index+1, compareIndex ,size, result, count, startTime, endTime);

    }
}

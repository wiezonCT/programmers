package wy.dfs;

public class target {
    public static void main(String[] args){
        int[] numbers = {1,1,1,1,1};
        int target = 3;
        
        System.out.println(bfs(numbers, target,  0));
    }

    public static int bfs(int[] numbers,int target, int index){
        int answer = 0;
        if(index == numbers.length){
            int sum = 0;
            for (int i : numbers) {
                sum += i;
            }
            if(sum == target) return 1;
            return 0;
        }else{
            answer+= bfs(numbers, target, index+1);
            numbers[index] *= -1;
            answer+= bfs(numbers, target, index+1);
            return answer;
        }
    }
    
}

package wy.kakao;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 표편집 {
    public static final String UP = "U";
    public static final String DOWN = "D";
    public static final String REMOVE = "C";
    public static final String UNDO = "Z";

    public static void main(String[] args){
        int size = 8; // 전체 size
        int cur = 2; // 현재위치 
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", 
    "Z", "Z"};

        //init
        char[] result = resultInit(size);

        Stack<Integer> stack = new Stack();
        List<Integer> removeIndex = new ArrayList<>();
        
        for (String command : cmd) {
            String[] split = command.split(" ");
            
            switch(split[0]){
                case UP:
                    cur -= Integer.parseInt(split[1]);
                    break;
                case DOWN:
                    cur += Integer.parseInt(split[1]);
                    break;
                case UNDO:
                    stack.pop();
                    break;
                case REMOVE:
                    stack.push(cur);
                    break;
            }    
        }

        while(!stack.isEmpty()){
            removeIndex.add(stack.pop());
        }
        
        for (Integer index : removeIndex) {
            result[index] = 'X';
        }

        System.out.println(result);
    }

    private static char[] resultInit(int size) {
        char[] arr = new char[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 'O';
        }
        return arr;
    }
}

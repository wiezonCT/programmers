import java.util.Arrays;

public class Boat {
    public static void main(String[] args){
        int[] people = {70,50,80,50};
        int limit = 100;

        Arrays.sort(people);
        
        boolean[] used = new boolean[people.length];
        int boatCount = 0;
        
        for (int j = people.length - 1; j >= 0; j--) {
            if (used[j]) continue;
            used[j] = true;
            
            for (int i = 0; i < j; i++) {
                if (!used[i] && people[i] + people[j] <= limit) {
                    used[i] = true;
                    break;
                }
            }
            boatCount++;
        }
        
        System.out.println(boatCount);
    }
}

import java.util.Arrays;

public class Boat {
    public static void main(String[] args){
        int[] people = {70,50,80,50};
        int limit = 100;

        Arrays.sort(people);
        
        int i = 0;
        int j = people.length - 1;
        int boatCount = 0;
        
        while (i <= j) {
            if (people[i] + people[j] <= limit) {
                i++;
            }
            
            j--;
            boatCount++;
        }
        
        System.out.println(boatCount);
    }
}

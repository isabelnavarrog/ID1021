import java.awt.*;
import java.util.Random;

public class duplicates {

    private static int[] sorted(int n) { //esto me crea una lista ordenada
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = 0;
        for (int i = 0; i < n ; i++) {
            nxt += rnd.nextInt(10) + 1; //cada vez voy sumando un numero entre 1 y 10
            array[i] = nxt;
        }
        return array;
    }
    public static boolean binary_search(int [] array, int key){
        int first = 0;
        int last= array.length-1;
        while (true){
            int index= (first+last)/2;
            if (array[index]==key){
                return true;
            }
            if (array[index] < key && index < last){
                first = index + 1;
                continue;
            }
            if (array[index] > key && index > first){
                last = index-1;
                continue;
            }
            break;
        }
        return false;
    }
    public static int duplicates(int [] array, int [] duplicate){
        int num_dup = 0;
        for (int i =0; i < array.length ; i++){
            int key = array[i];
            binary_search(duplicate,key);
            if (binary_search(duplicate, key) == true){
                num_dup++;
            }
        }
        return num_dup;
    }
    public static void time_duplicates(int tries, int n){
        int [] array = sorted(n);
        int [] duplicate = sorted(n);
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < tries; i++){
            double n0 = System.nanoTime();
            duplicates(array,duplicate);
            double n1 =  System.nanoTime();
            double t = (n1 - n0);
            if ( t < min){
                min = t;
            }
            if (t > max){
                max = t;
            }
            sum += t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, sum/tries, max);
    }
    public static void main(String[] args) {
        int[] sizes = {200,400,800,1600,3200,6400,12800,25600};
        for ( int n : sizes) {
            time_duplicates(10_000, n);
        }
    }
}



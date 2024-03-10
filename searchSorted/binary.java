import java.util.Random;
import java.util.Arrays;

public class binary {
    //we first create the sorted array
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
                first = index+1;
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

    public static void time_binary(int tries, int n){
        int [] array = new int[n];
        array = sorted(n);
        int [] keys = new int[tries];
        keys =sorted(tries);
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < tries; i++){
            int k = keys[i];
            double n0 = System.nanoTime();
            binary_search(array, k);
            double n1 =  System.nanoTime();
            double t = (n1 - n0);
            if ( t < min){
                min = t;
            }
            if (t > max ){
                max = t;
            }
            sum += t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, sum/tries, max);
    }
    public static void main(String[] args) {
        int[] sizes = {200,400,800,1600,3200,6400};
        for ( int n : sizes) {
            time_binary(10_000, n);
        }
    }
}

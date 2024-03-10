import java.util.Random;
import java.util.Arrays;

public class insert {
    public static int [] unsorted(int n){
        int[] array = new int[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++){
            array[i]=rnd.nextInt(n);
        }
        return array;
    }
    public static int [] insert(int [] array){
        for (int i = 0; i < array.length ; i++) {
            for (int j = i; j > 0 && array[j]<array[j-1]; j--) {
                if (array[j] < array[j-1]) {
                    int swap = array [j];
                    array[j] = array[j-1];
                    array[j-1]=swap;
                }
            }
        }
        return array;
    }

    public static void time(int n){
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < 1_000; i++){
            int [] array = unsorted(n);
            double t0 = System.nanoTime();
            insert(array);
            double t1 = System.nanoTime();
            double t = t1-t0;
            if (t < min){
                min = t;
            }
            if (t > max ){
                max = t;
            }
            sum+=t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, sum/1_000, max);
    }

    public static void main(String[] args){
        int[] sizes = {100,200,400,800,1600};

        for ( int n : sizes) {
            time(n);
        }

    }
}

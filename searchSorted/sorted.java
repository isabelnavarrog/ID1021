import java.util.Random;

public class sorted {
    private static int[] sorted(int n) { //esto me crea una lista ordenada
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = 0;
        for (int i = 0; i < n ; i++) {
            nxt += rnd.nextInt(10) + 1; //cada vez voy sumando un numero entre 1 y 10
            array[i] = nxt;
//            System.out.println(array[i]);
        }
        return array;
    }
    public static boolean search_sorted(int[] array, int key){
        for (int index=0; index < array.length;index++){
            if (array[index] == key){
                return true;
            }
            if (array[index] > key){
                return false;
            }
        }
        return false;
    }

    public static void time_sorted(int tries, int n) {
        Random rnd = new Random();
        int[] array = sorted(n); //create the unsorted array with n elements
        int[] keys = new int[tries]; //each time we run the program we look for a new key
        for (int j = 0; j < tries; j++) {
            keys[j] = rnd.nextInt(tries);//we create a new array w/int-keys
        }
        double min = Double.POSITIVE_INFINITY; //we set the minimum time to be inf
        double max = 0; //we set the maximum time to be 0
        double sum = 0; //the sum beggins in 0
        for (int k = 0; k < tries; k++) {
            int key = keys[k]; //we first look for the key
            //here we start the "clock"
            //we want to know how long it takes to search for the key
            double n0 = System.nanoTime();
            search_sorted(array, key);
            double n1 = System.nanoTime();
            //here we end the clock as we've found the time
            double t = (n1 - n0);
            sum += t;
            //the time taken will be the difference end-initial
            if (t < min) {
                min = t;
            }
            if (t > max) {
                max = t;
            }
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, sum/tries, max);
    }
    public static void main(String[] args) {
        int[] sizes = {200,400,800,1600,3200,6400,12800,25600};
        for ( int n : sizes) {
            time_sorted(100_000, n);
        }
    }

}
import java.util.Random;

public class unsorted {
    public static boolean search_unsorted(int[] array, int key) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == key) {
                return true;
            }
        }
        return false;
    }
    //para buscar un cierto numero en una lista no ordenada la unica solucion es recorrer la lista entera
    public static void time_unsorted(int tries, int n) {
        Random rnd = new Random();
        int[] array = new int[n]; //create the unsorted array with n elements
        int[] keys = new int[tries]; //each time we run the program we look for a new key
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(n); //we create a new array w/int
        }
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
            search_unsorted(array, key);
            double n1 = System.nanoTime();
            //here we end the clock as we've found the time
            double t = (n1 - n0);
            //the time taken will be the difference end-initial
            if (t < min) {
                min = t;
            }
            if (t > max) {
                max = t;
            }
            sum += t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, (sum)/(tries), max);
    }
    public static void main(String[] arg) {
        int[] sizes = {1000  , 2000, 3000,4000, 5000};
        for ( int n : sizes) {
            time_unsorted(10_000, n);
        }
    }
}

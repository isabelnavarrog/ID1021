import java.util.Random;
public class section_sort {

    public static int [] unsorted(int n){
        int[] array = new int[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++){
            array[i]=rnd.nextInt(50);
        }
        return array;
    }
    public static int [] section_sort(int [] array){
        for (int i = 0; i < array.length -1; i++) {
            int cand = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[cand]) {
                    cand = j;
                }
            }
            int smaller = array[cand];
            array[cand] = array[i];
            array[i] = smaller;
        }
        return array;
    }

    public static void benchmark(int n){
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < 1_000; i++){
            int [] array = unsorted(n);
            double t0 = System.nanoTime();
            section_sort(array);
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
            benchmark(n);
        }

    }

}

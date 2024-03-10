import java.util.Random;
import java.util.Arrays;
public class quick_sort {

    public static int [] unsorted(int n){
        int[] array = new int[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++){
            array[i]=rnd.nextInt(50);
        }
        return array;
    }

    public static void quick_sort(int [] array) {
        quick_Sort(array, 0, array.length-1);
    }

    private static void quick_Sort(int[] array, int lo, int hi){
        if(lo < hi+1){
            int p=partition(array, lo, hi);
            quick_Sort(array, lo,p-1);
            quick_Sort(array, p+1, hi);
        }
    }
    private static void swap(int [] array, int index1, int index2){
        int temp=array[index1];
        array[index1]=array[index2];
        array[index2]=temp;
    }
    private static int getPivot(int lo, int hi){
        Random rnd= new Random();
        return rnd.nextInt((hi-lo)+1)+lo;
    }
    private static int partition(int [] array, int lo, int hi){
        int pivot=getPivot(lo,hi);
        swap(array,lo,pivot);
        int border = lo + 1;
        for (int i = border; i <= hi; i++){
            if(array[i]<array[lo]){
                swap(array, i, border++);
            }
        }
        swap(array, lo, border-1);
        return border-1;
    }
    public static void time(int n){
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < 1_000; i++){
            int [] org = unsorted(n);
            double t0 = System.nanoTime();
            quick_sort(org);
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
        int[] sizes = {100,200,300,400,500};
        for ( int n : sizes) {
            time(n);
        }

    }

}

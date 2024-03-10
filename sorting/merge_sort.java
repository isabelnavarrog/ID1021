import java.util.Random;
import java.util.Arrays;

public class merge_sort {
    public static int [] unsorted(int n){
        int[] array = new int[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++){
            array[i]=rnd.nextInt(50);
        }
        return array;
    }
    public static void sort(int[] org) {
        if (org.length == 0)
            return;
        int[] aux = new int[org.length];
        sort(org, aux, 0, org.length -1);
    }
    private static void sort(int[] org, int[] aux, int lo, int hi) {
        if (lo != hi) {
            int mid = lo + (hi-lo)/2;
            int [] org1 = new int[mid+1];
            for (int i = lo; i < mid+1; i++){
                org1[i-lo]=org[i];
            }
            sort(org1);
            int l = hi-mid;
            int [] org2 = new int [l];
            for (int j = mid+1; j < hi + 1  ; j++){
                org2[j-mid-1] = org[j];
            }
            sort(org2);
            for (int p = lo; p < hi + 1 ; p++){
                if (p< mid+1){
                    org[p]=org1[p];
                }
                else{
                    org[p]=org2[p-mid-1];
                }
            }
            merge(org, aux, lo, mid, hi);
        }
    }
    private static void merge(int[] org, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi;i++) {
            aux[i] = org[i];
        }
        //let's do the merging
        int i = lo; // the index in the first part
        int j = mid+1; // the index in the second part
        for ( int k = lo; k <= hi; k++) {
            // if i is greater than mid, move the j item to the org array, update j
            if (i>mid){
                org[k]=aux[j];
                j++;
            }
            // else if j is greate than hi, move the i item to the org array, update i
            else if ( j > hi){
                org [k] = aux[i];
                i++;
            }
            // else if the i item is smaller than the j item,
            // move it to the org array, update i
            else if (aux [i] < aux[j]){
                org [k] = aux [i];
                i++;
            }
            // else you can move the j item to the org array, update j
            else {
                org [k] =aux[j];
                j++;
            }
        }
    }
    public static void time(int n){
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < 1_000; i++){
            int [] org = unsorted(n);
            double t0 = System.nanoTime();
            sort(org);
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

//    public static void main(String[] Args){
//        int [] org= unsorted(20);
//        System.out.println(Arrays.toString(org));
//        sort(org);
//        System.out.println(Arrays.toString(org));
//    }
}

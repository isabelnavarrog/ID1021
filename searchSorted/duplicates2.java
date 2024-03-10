import java.util.Arrays;
import java.util.Random;

public class duplicates2 {
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
        public static int duplicates2(int [] array, int [] duplicate){
            int first = 0;
            int second = 0;
            int sum = 0;
            while (first<array.length && second<array.length){
                if (array[first]>duplicate[second]){
                    second++;
                } else if (array[first]==duplicate[second]) {
                    sum++;
                    first++;
                    second++;
                }
                else{
                    first++;
                }
            }
            return sum;
        }
        public static void time_duplicates(int tries, int n){
            int [] array = sorted(n);
            int [] duplicate = sorted(n);
            double min = Double.POSITIVE_INFINITY;
            double max = 0;
            double sum = 0;
            for (int i = 0; i < tries; i++){
                double n0 = System.nanoTime();
                duplicates2(array,duplicate);
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
            int[] sizes = {200,400,800,1600,3200};
            for ( int n : sizes) {
                time_duplicates(10_000, n);
            }
        }

}

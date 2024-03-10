import java.util.Random;

public class Main4 {
    private static double duplicates(int n){
        int k = 1_000; //number of times we want to do the operation
        Random rnd = new Random();
        int [] array = new int[n];
        int [] arraydup = new int[n]; // we built 2 arrays with the same length
        for (int l = 0;  l< n; l++) {
            array[l] = rnd.nextInt(2 * n);
            arraydup[l] = rnd.nextInt(2 * n);
        }
        int sum = 0;
        double t_total = 0;
        for (int i = 0; i < k; i++){
            long t0 = System.nanoTime();
            for (int j = 0; j <n; j++){
                int search_d = array[j]; // number to find
                for (int ki = 0; ki < n ; ki++){
                    if (arraydup[ki]==search_d){
                        sum++;
                        break;
                    }
                }
            }
            t_total += (System.nanoTime()-t0);
        }
        return  t_total/(k);
    }
    public static void main(String[] args){
        System.out.println(Main4.duplicates(100));
        System.out.println(Main4.duplicates(200));
        System.out.println(Main4.duplicates(300));
        System.out.println(Main4.duplicates(400));
    }
}

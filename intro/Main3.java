import java.util.Random;

public class Main3 {
    private static double search(int n) {
        int k = 1_000; //this is the number of rounds we want to execute
        int m = 1_000; //this is the number of keys we want to search for
        Random rnd = new Random();
        int[] keys = new int[m];
        int[] array = new int[n];
        int sum = 0;
        double t_total=0;
        for (int j = 0; j < k; j++){
            for (int l = 0; l < m; l++) {
                keys[l] = rnd.nextInt(10 * n);
            }
            for (int i = 0; i < n; i++) {
                array[i] = rnd.nextInt(10 * n);
            }
            long t0 = System.nanoTime();
            for (int ki = 0; ki < m; ki++) {
                int key = keys[ki];
                for (int i = 0; i < n; i++) {
                    if (array[i] == key) {
                        sum++;
                        break;
                    }
                }
            }
            t_total += (System.nanoTime() - t0);
        }
        return t_total/(k*m);
    }
    public static void main(String[] args){
        System.out.println(Main3.search(200));
        System.out.println(Main3.search(400));
        System.out.println(Main3.search(800));
        System.out.println(Main3.search(1600));

    }
}



import java.util.Random;
public class introTask1 {
    private static double access(int n) {
        int k = 1_000_000;
        int l = n;
        Random rnd = new Random();
        int[] indx = new int[l];
        int[] array = new int[n];
        for ( int i = 0; i < n; i++) {
            indx[i] = rnd.nextInt(n); //we create an array with random integers. Could be repeated.
            array[i] = 1; //we create an array with dummy elements. All ones.
        }
        int sum = 0;
        long t0 = System.nanoTime();
        //long t_access = 0;
        for (int j = 0; j < k; j++) {
            for (int i = 0; i < l; i++) {
                sum += array[indx[i]]; //we do a loop where we add the array elements following a random order
            }
            //t_access += (System.nanoTime() - t0); //in each loop we add up the time it takes to do the operation
        }
        double t_access = (System.nanoTime() - t0);
        int sumdummy = 0;
        long t1 = System.nanoTime();
        //long t_dummy = 0;
        for (int j = 0; j < k; j++){
            for (int i = 0; i < l; i++) {
                sumdummy += array[i]; //we do a loop where we add the array elements following a sequential order
            }
            //t_dummy += (System.nanoTime() - t1); //in each loop we add up the time it takes to do the operation
        }
        double t_dummy = (System.nanoTime() - t1);
        return ((double)(t_access - t_dummy))/((double)k*(double)l); //we get an average of the time difference
        //we are taking into account the number of times the loop is being made as well as the size of the array.
    }
    public static void main(String[] args){

        System.out.println(introTask1.access(10));
        System.out.println("VARIABLES GENERADAS...");
        System.out.println(introTask1.access(10));
        System.out.println(introTask1.access(100));
        System.out.println(introTask1.access(1_000));
        System.out.println(introTask1.access(10_000));
        System.out.println(introTask1.access(50_000));
    }
}

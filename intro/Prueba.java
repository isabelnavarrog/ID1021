import java.util.Random;
import java.util.Arrays;
public class Prueba {
    public static void main(String[] args){
        Random rnd = new Random();
        int[] array = new int[10];
        int[] array2 = new int[10];
        for(int j = 0; j < 10; j++){
            array[j]= rnd.nextInt(20);
            array2[j]= rnd.nextInt(20);
        }
        int[] a= new int[]{array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9]};
        int[] a2= new int[]{array2[0],array2[1],array2[2],array2[3],array2[4],array2[5],array2[6],array2[7],array2[8],array2[9]};
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(a2));
        int sum =0;
        for(int i = 0; i < 10; i++){
            int search_d = array[i];
            for (int ki = 0; ki < 10 ; ki++){
                if (array2[ki]==search_d){
                    sum++;
                    break;
                }
            }
        }
        System.out.println(sum);
    }
}

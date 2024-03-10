import java.util.Random;
public class quicksort {
    public static int [] swap(int [] array, int i, int j){
        int swap = array[i];
        array[i]=array[j];
        array[j]=swap;
        return  array;
    }
    public static int partition(int[] array, int i, int j){
        int org_i = i;
        int org_j = j;
        int pivot = array[i];
        int index = i;
        while (i<array.length && array[i] <= pivot ){
            i++;
        }
        while(j>0 && array[j]>pivot){
            j--;
        }
        if (i <= j){
            array=swap(array,i,j);
            index = partition(array, org_i, org_j);
        }
        else{
            array = swap(array, org_i,j);
            index = j;
        }
        return index;
    }
    public static int partition_2(int[] array, int i, int j) {
        int pivot = array[i];
        int pointer = i;
        int end = j;
        while (pointer != end+1){
            while ((pointer <= end)&&(array[pointer]<=pivot)){
                pointer++;
            }
            while((pointer<=end) && (array[end]>=pivot)){
                end--;
            }
            if (pointer < end){
                swap(array, pointer, end);
                pointer++;
                end--;
            }
        }
        swap(array, i, end);
        int index =end;
        return index;
    }
    public static int [] sort(int[] array, int i, int j){
        if (i == j){
            return array;
        }
//       else if (i == j-1){
//           if(array[i]>array[j]){
//               array = swap(array, i, j);
//           }
//        }
        else{
            int index =partition_2(array, i, j);
            System.out.println(index);
            //we have two extreme cases
            if (index==i){
                //if index == 0 it means that all the items in the array
                //are bigger than our pivot and we have to sort that part
                array = sort(array,index+1,j);
            }
            else if (index == j) {
                //if index==array.length-1, all the items in the array are
                //smaller than our pivot, we have to sort that part
                array = sort(array,i,index-1);
            }
            else{
                //if we are not in any of those cases, we sort the array separately
                array = sort(array,i,index-1);
                array = sort(array,index+1,j);
            }
            return array;
        }
    }

    public static void time(int tries, int n){
        Random rnd = new Random();
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int k = 0; k < tries; k++){
            int [] array = new int[n];
            for (int l = 0; l < array.length; l++){
                array[l] = rnd.nextInt(2*n);
            }
            double t0 = System.nanoTime();
            sort(array, 0, array.length-1);
            double t1 = System.nanoTime();
            double t = (t1-t0);
            if (t < min)
                min = t;
            if (t > max)
                max = t;
            sum+=t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, (sum)/tries, max);
    }

    public static void main(String [] args){
        Random rnd = new Random();
        int [] array = new int[100];
        for (int l = 0; l < array.length; l++){
            array[l] = rnd.nextInt(2*100);
        }
        int[] arr = sort(array,0,array.length-1);
        System.out.println("-----");
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
//        int [] sizes = {100,200,400,800,1600,3200};
//        for (int s : sizes){
//            time(100_000,s);
//        }
    }


}

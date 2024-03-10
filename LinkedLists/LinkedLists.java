import java.util.Random;

public class LinkedLists {
    int head;
    LinkedLists tail;
    public LinkedLists(int item, LinkedLists list){
        head = item;
        tail = list;
    }
    public int head(){
        return this.head;
    }
    public LinkedLists tail(){
        return this.tail;
    }
    public static LinkedLists create(int n) {
       Random rnd = new Random();
        LinkedLists a = new LinkedLists(0, null);
        LinkedLists bef = a;
        for (int i = 0; i < n; i++) {
            bef = new LinkedLists(rnd.nextInt(n), bef);
//            bef = new LinkedLists(0, bef);
        }
        return bef;
    }
    public void append (LinkedLists b){
        LinkedLists nxt = this;
        while (nxt.tail != null){
            nxt = nxt.tail;
        }
        nxt.tail=b;
    }
    public int remove (){
        LinkedLists nxt = this;
        LinkedLists a= new LinkedLists(nxt.head,null);
        while (nxt.tail != null){
            nxt = nxt.tail;
            if (nxt.tail != null) {
                System.out.println(nxt.head);
                a.append(new LinkedLists(nxt.head, null));
            }
        }
        this.head=a.head;
        this.tail=a.tail;
        return nxt.head;
    }



    public static void time_create(int n, int tries){
        //n is the size of the LinkedList
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < tries; i++){
            double n0 = System.nanoTime();
            create(n);
            double n1 = System.nanoTime();
            double t = (n1-n0);
            if (t < min){
                min = t ;
            }
            if (t > max){
                max = t;
            }
            sum += t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, (sum)/(tries), max);
    }
    public static void time_fixedb(int n, int tries){
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < tries; i++){
            LinkedLists b = create(1000);
            LinkedLists a=create(n); // I create a list of n elements
            double n0 = System.nanoTime();
            b.append(a);
            double n1 = System.nanoTime();
            double t = (n1-n0);
            if (t < min){
                min = t ;
            }
            if (t > max){
                max = t;
            }
            sum += t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, (sum)/(tries), max);
    }
    public static void time_fixeda(int n, int tries){
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < tries; i++){
            LinkedLists a = create(1000);
            LinkedLists b = create(n);
            double n0 = System.nanoTime();
            b.append(a);
            double n1 = System.nanoTime();
            double t = (n1-n0);
            if (t < min){
                min = t ;
            }
            if (t > max){
                max = t;
            }
            sum += t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, (sum)/(tries), max);
    }
    public static int[] create_array(int n) {
        Random rnd = new Random();
        int [] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i]=rnd.nextInt(n);
        }
        return array;
    }

    public static int[] allocate(int [] array) {
        int [] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i]=array[i];
        }
        return copy;
    }


    public static int[] append_arrays(int[] array1, int [] array2){
        int n = array1.length;
        int m = array2.length;
        int[] array = new int[n+m];
        for (int i = 0; i <array.length; i++){
            if (i<n){
                array[i]= array1[i];
            }
            else{
                array[i]=array2[i-n];
            }
        }
        return array;
    }

    public static void time1(int n, int tries){
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < tries; i++){
            int [] array1 = new int[1000];
            int [] array2 = new int[n];
            double n0 = System.nanoTime();
            int [] array = append_arrays(array1,array2);
            double n1 = System.nanoTime();
            double t = (n1-n0);
            if (t < min){
                min = t ;
            }
            if (t > max){
                max = t;
            }
            sum += t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, (sum)/(tries), max);
    }

    public static void time2(int n, int tries){
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < tries; i++){
            int [] array1 = new int[n];
            int [] array2 = new int[1000];
            double n0 = System.nanoTime();
            int [] array = append_arrays(array1,array2);
            double n1 = System.nanoTime();
            double t = (n1-n0);
            if (t < min){
                min = t ;
            }
            if (t > max){
                max = t;
            }
            sum += t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, (sum)/(tries), max);
    }

    public static void time_allocate(int n, int tries){
        //n is the size of the LinkedList
        int [] array  = create_array(n);
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < tries; i++){
            double n0 = System.nanoTime();
            allocate(array);
            double n1 = System.nanoTime();
            double t = (n1-n0);
            if (t < min){
                min = t ;
            }
            if (t > max){
                max = t;
            }
            sum += t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, (sum)/(tries), max);
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 2000, 4000, 8000, 16000};
        /*System.out.print("Fixed b and varying a \n");
        for (int n : sizes) {
          time_fixedb(n, 10_000);
        }
        System.out.print("Other way around \n");
        for (int n : sizes) {
            time_fixeda(n, 10_000);
        }*/
       System.out.print("For arrays vary 2\n");
        for (int n : sizes) {
            time1(n, 10_000);
        }
        System.out.print("For arrays vary 1\n");
        for (int n : sizes) {
            time2(n, 10_000);
        }
        System.out.print("Create a Linked List\n");
        for (int n : sizes) {
            time_create(n, 10_000);
        }
        System.out.print("Create an array\n");
        for (int n : sizes) {
            time_allocate(n, 10_000);
        }
    }
}

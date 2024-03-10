import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Random;

public class Zip3 {
    Node3[] data;
    int max;
    public class Node3{
        Integer code;
        String name;
        Integer pop;
        public Node3(Integer zip, String area, Integer population){
            code = zip;
            name = area;
            pop = population;
        }
        public Integer code(){
            return this.code;
        }
        public String name(){
            return this.name;
        }
        public Integer pop(){
            return this.pop;
        }
    }
    public Zip3(String file) {
        data = new Node3[100000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                data[code] = new Node3(code, row[1], Integer.valueOf(row[2]));
                i++;
            }
            max = i-1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }
    public Node3[] data(){
        return this.data;
    }
    public int max(){
        return this.max;
    }
    public int [] keys(){
        int [] keys = new int[max+1];
        int index = 0 ;
        for (int i = 0; i < this.data.length; i++){
            if (this.data[i] != null){
                keys[index] = this.data[i].code;
                index++;
            }
        }
        return keys;
    }
    public int collisions(int mod) {
        int [] keys = this.keys();
        int[] data = new int[mod];
        int[] cols = new int[100000/mod];
        int num_cols = 0;
        for (int i = 0; i < max; i++) {
            Integer index = keys[i] % mod;
            cols[data[index]]++;
            data[index]++;
            if (data[index]>1){
                num_cols++;
            }
        }
//        System.out.print(mod);
//        System.out.println("\n");
//        for (int i = 0; i < cols.length; i++) {
//            System.out.print("\t" + cols[i]);
//        }
//        for (int i = 0; i < data.length; i++){
//            System.out.println(data[i]);
//        }
//        System.out.println();
        return num_cols;
    }
    public LinkedList<Node3>[] hash_table(int mod){
        LinkedList<Node3>[] bucket_arr = new LinkedList[mod];
        for (int  i = 0; i < this.data.length ; i++){
            if (this.lookup_iterative3(i)){
                Integer index = i % mod;
                Node3 nod = this.data[i];
                if (bucket_arr[index] == null){
                    bucket_arr[index] = new LinkedList<Node3>();
                    bucket_arr[index].add(nod);
                }
                else{
                    bucket_arr[index].add(nod);
                }
            }
        }
        return bucket_arr;
    }

    public boolean lookup3(Integer key, int index){
        int i = index;
        while (this.data[i] == null && i<this.data.length ){
            if (i > key){
                return false;
            }
            i++;
        }
        if (i == this.data.length-1){
            return false;
        } else if (i == key) {
            return true;
        } else {
            return lookup3(key, i + 1);
        }
    }
    public boolean lookup_iterative3(Integer key){
        for (int i = 0; i < this.data.length; i++){
            if (this.data[i] != null){
                if (i == key){
                    return true;
                }
            }
        }
        return false;
    }
    public Node3[] hash2(int mod){
        Node3 [] data = new Node3[mod];
        int [] keys = this.keys();
        Node3 [] cols = new Node3[this.collisions(mod)];
        int c = 0;
        for (int i = 0; i < max; i++){
            Integer index = keys [i] % mod;
            if (data[index] == null){
                data[index] = this.data[keys[i]];
            } else{
                cols[c] = this.data[keys[i]];
                c++;
            }
        }
        for (int k = 0; k < c; k++){
            int index2 = cols[k].code % mod;
            while ((index2 < data.length) && (data[index2] != null)){
                index2 ++;
            }
            if (index2 == data.length){
                index2 = 0;
                while ((index2 < (cols[k].code % mod))  && (data[index2] != null)){
                    index2 ++;
                }
            }
            data[index2] = cols[k];
        }
        return data;
    }
    public  int relocate (int key, int mod){
        int i = 0;
        Node3[] tab = this.hash2(mod);
        while ( i< mod){
            if (tab[i] != null &&tab[i].code == key)
                break;
            i++;
        }
        return i;
    }
    public void time(int tries, Integer key) {
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int k = 0; k < tries; k++) {
            double n0 = System.nanoTime();
            this.lookup3(key, 0);
            double n1 = System.nanoTime();
            double t = (n1 - n0);
            if (t < min)
                min = t;
            if (t > max)
                max = t;
            sum += t;
        }
        System.out.printf("The time to search for "+ key+ " is min: %.2fns\t avg: %.2fns\t max: %.2fns\n", min, sum/tries, max);
    }
    public void time_iterative3(int tries, Integer key) {
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int k = 0; k < tries; k++) {
            double n0 = System.nanoTime();
            this.lookup_iterative3(key);
            double n1 = System.nanoTime();
            double t = (n1 - n0);
            if (t < min)
                min = t;
            if (t > max)
                max = t;
            sum += t;
        }
        System.out.printf("The time to search for "+ key+ " is min: %.2fns\t avg: %.2fns\t max: %.2fns\n", min, sum/tries, max);
    }
    public void timehash(int tries, int mod) {
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int k = 0; k < tries; k++) {
            double n0 = System.nanoTime();
            this.hash_table(mod);
            double n1 = System.nanoTime();
            double t = (n1 - n0);
            if (t < min)
                min = t;
            if (t > max)
                max = t;
            sum += t;
        }
        System.out.printf("The time when we take module "+ mod+ " is min: %.2fns\t avg: %.2fns\t max: %.2fns\n", min, sum/tries, max);
    }
    public void timehash2(int tries, int mod) {
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int k = 0; k < tries; k++) {
            double n0 = System.nanoTime();
            this.hash2(mod);
            double n1 = System.nanoTime();
            double t = (n1 - n0);
            if (t < min)
                min = t;
            if (t > max)
                max = t;
            sum += t;
        }
        System.out.printf("The time when we take module "+ mod+ " is min: %.2fns\t avg: %.2fns\t max: %.2fns\n", min, sum/tries, max);
    }
    public static void main(String [] args){
        Zip3 z = new Zip3("postnummer.csv");
        Random rnd = new Random();
        for (int i = 0; i < 4; i++){
            int n= rnd.nextInt(11115, 98499);
            System.out.println(n);
            System.out.println(z.relocate(n,10000));
            System.out.println(z.relocate(n,20000));
            System.out.println(z.relocate(n,12345));
            System.out.println("........");
        }
//        z.timehash(1,10000);
//        z.timehash(1,20000);
//        z.timehash(1, 12345);
//        System.out.println("......");
//        z.timehash2(1,10000);
//        z.timehash2(1,20000);
//        z.timehash2(1, 12345);

//        int [] k = z.keys();
//        System.out.println(z.collisions(10000));
//        System.out.println(".....\n");
//        System.out.println(z.collisions(20000));
//        System.out.println("a random number\n");
//        System.out.println(z.collisions(12345));
//        System.out.println(".....");
//        System.out.println(z.collisions(23153));
//        System.out.println(".....\n");
//        z.collisions(24013);
//        Node3[] nod = z.hash2(10000);
        //LinkedList<Node3>[] l = z.hash_table(10000);
//        System.out.println("-------------RECURSIVE--------\n");
//        z.time(10_000, 11115);
//        System.out.println("........");
//        z.time(10_000, 99499);
//        System.out.println("-------------ITERATIVE--------\n");
//        z.time_iterative3(10_000, 11115);
//        System.out.println("........");
//        z.time_iterative3(10_000, 99499);
    }
}

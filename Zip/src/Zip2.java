import java.io.BufferedReader;
import java.io.FileReader;

public class Zip2 {
    Node2[] data;
    int max;
    public class Node2{
        int code;
        String name;
        Integer pop;
        public Node2(int zip, String area, Integer population){
            code = zip;
            name = area;
            pop = population;
        }
        public int code(){
            return this.code;
        }
        public String name(){
            return this.name;
        }
        public Integer pop(){
            return this.pop;
        }
    }
    public Zip2(String file) {
        data = new Node2[10000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                data[i++] = new Node2(code, row[1], Integer.valueOf(row[2]));
            }
            max = i-1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }
    public Node2[] data(){
        return this.data;
    }
    public int max(){
        return this.max;
    }
   public boolean lookup2(int key, int i, int j){
       if (this.data[i].code == key || this.data[j].code == key){
           return true;
       }
       if (i == j && this.data[i].code != key ) {
           return false;
       }
       if (this.data[i].code < key || this.data[i].code > key){
           return false;
       }
       else {
           int mid =(i+j)/2;
           if (this.data[mid].code ==  key ){
               return true;
           } else if(this.data[mid].code > key){
               return lookup2(key, i, mid-1);
           }else{
               return lookup2(key, mid+1, j);
           }
       }
   }

    public boolean lookup_iterative2(int key) {
        int start = 0;
        int end = this.max;
        while (true) {
            if (this.data[start].code == key || this.data[end].code == key)
                return true;
            int mid = (start + end) / 2;
            if (this.data[mid].code== key) {
                return true;
            }
            if (this.data[mid].code > key && mid > start) {
                end = mid - 1;
                continue;
            }
            if (this.data[mid].code < key  && mid < end) {
                start = mid + 1;
                continue;
            }
            break;
        }
        return false;
    }
    public void time(int tries, int key) {
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int k = 0; k < tries; k++) {
            double n0 = System.nanoTime();
            this.lookup2(key, 0, this.max);
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
    public void time_iterative2(int tries, int key) {
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int k = 0; k < tries; k++) {
            double n0 = System.nanoTime();
            this.lookup_iterative2(key);
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
    public static void main(String[] args) {
        Zip2 z = new Zip2("postnummer.csv");
        System.out.println("-------------RECURSIVE--------\n");
        z.time(10_000, 11115);
        System.out.println("........");
        z.time(10_000, 99499);
        System.out.println("-------------ITERATIVE--------\n");
        z.time_iterative2(10_000, 11115);
        System.out.println("........");
        z.time_iterative2(10_000, 99499);
    }
}

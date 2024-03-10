import java.io.BufferedReader;
import java.io.FileReader;
public class Zip {
    Node [] data;
    int max;
    public class Node{
        String code;
        String name;
        Integer pop;
        public Node(String zip, String area, Integer population){
            code = zip;
            name = area;
            pop = population;
        }
        public String code(){
            return this.code;
        }
        public String name(){
            return this.name;
        }
        public Integer pop(){
            return this.pop;
        }
    }
    public Zip(String file) {
//        data = new Node[10000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
//                data[i++] = new Node(row[0], row[1], Integer.valueOf(row[2]));
            }
            max = i-1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }
    public Node[] data(){
        return this.data;
    }
    public int max(){
        return this.max;
    }
    public boolean lookup(String key, int i, int j){
        if (this.data[i].code.equals(key) || this.data[j].code.equals(key)){
            return true;
        }
        if (i == j && !this.data[i].code.equals(key)) {
            return false;
        }
        if (this.data[j].code.compareTo(key)>0 || this.data[i].code.compareTo(key)<0){
            return false;
        }
        else {
            int mid =(i+j)/2;
            if (this.data[mid].code.equals(key)){
                return true;
            } else if(this.data[mid].code.compareTo(key)>0){
                return lookup(key, i, mid-1);
            }else{
                return lookup(key, mid+1, j);
            }
        }
    }
    public boolean lookup_iterative(String key) {
        int start = 0;
        int end = this.max;
        while (true) {
            if (this.data[start].code.equals(key) || this.data[end].code.equals(key))
                return true;
            int mid = (start + end) / 2;
            if (this.data[mid].code.equals(key) ) {
                return true;
            }
            if (this.data[mid].code.compareTo(key) > 0 && mid > start) {
                end = mid - 1;
                continue;
            }
            if (this.data[mid].code.compareTo(key) < 0 && mid < end) {
                start = mid + 1;
                continue;
            }
            break;
        }
        return false;
    }

    public void time(int tries, String key) {
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int k = 0; k < tries; k++) {
            double n0 = System.nanoTime();
            this.lookup(key, 0, this.max);
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
    public void time_iterative(int tries, String key) {
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int k = 0; k < tries; k++) {
            double n0 = System.nanoTime();
            this.lookup_iterative(key);
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
        Zip z = new Zip("trains.csv");
//        System.out.println("-------------RECURSIVE--------\n");
//        z.time(10_000, "111 15");
//        System.out.println("........");
//        z.time(10_000, "994 99");
//        System.out.println("-------------ITERATIVE--------\n");
//        z.time_iterative(10_000, "111 15");
//        System.out.println("........");
//        z.time_iterative(10_000, "994 99");
//        System.out.println(z.lookup("111 15", 0, z.max));
//        System.out.println(z.lookup("994 99", 0, z.max));
    }
}

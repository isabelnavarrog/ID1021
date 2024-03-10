import java.util.Random;

public class simplyll {
    Nodes head;
    int size;
    public int size(){
        return this.size;
    }
    public Nodes head(){
        return this.head;
    }
    public simplyll(){
        head = null;
        size = 0;
    }
    public void addNodes(int newvalue) {
        Nodes newNode = new Nodes(newvalue);
        Nodes nxt = this.head;
        if(this.head == null) {
            head = newNode;
        }
        else {
            while (nxt.tail != null){
                nxt = nxt.tail;
            }
            nxt.tail = newNode;
        }
        this.size++;
    }
    public static simplyll creates(int n){
        Random rnd= new Random();
        simplyll firstlist = new simplyll();
        for (int i = 0; i < n; i++){
            firstlist.addNodes(rnd.nextInt(n));
        }
        return firstlist;
    }
    public void show(){
        Nodes nxt = this.head;
        while (nxt.tail != null){
            System.out.println(nxt.value);
            nxt = nxt.tail;
        }
        System.out.println(nxt.value);
    }

    public int remove(int k) {
        Nodes nxt = this.head;
        if (k == 0) {
            int out = head.value;
            this.head = head.tail;
            this.size--;
            return out;
        }
        else{
            for (int i = 0; i < k-1; i++){
                nxt = nxt.tail;
            }
            Nodes fuera = nxt.tail;
            int out = fuera.value;
            nxt.tail= fuera.tail;
            this.size--;
            return out;
        }
    }

    public static int[] index_seq(int k, int n){
        // n will be the list length
        //k will be the number of times we repeat a certain op
        int[] sequence = new int[k];
        Random rnd = new Random();
        for (int i = 0; i < k; i++) {
            sequence[i] = rnd.nextInt(n);
        }
        return sequence;
    }
    public static void times(int tries, int n){
        //n is the size of the LinkedList
        //we create a doublylinkedlist of n items
        simplyll a = creates(n);
        int [] seq = index_seq(tries,n);
        //we create a sequence of k integers - nºop
        //it contains random integers 0 - n
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < tries; i++){
            int out_index=seq[i];
            double n0 = System.nanoTime();
            int r = a.remove(out_index);
            double n1 = System.nanoTime();
            double t = (n1-n0);
            a.addNodes(r);
            if (t < min){
                min = t ;
            }
            if (t > max){
                max = t;
            }
            sum += t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, (sum)/tries, max);
    }
    public static void main(String[] args){
        int[] sizes = {100, 200, 400, 800, 1600};
        for (int n : sizes) {
            times(100_000, n);
        }
    }
}

import java.util.Random;
public class SimplyLinkedList {
    int head;
    SimplyLinkedList tail;
    public SimplyLinkedList(int item, SimplyLinkedList list){
        head = item;
        tail = list;
    }

    public int head(){
        return this.head;
    }
    public SimplyLinkedList tail(){
        return this.tail;
    }
    public static SimplyLinkedList create(int n) {
        Random rnd = new Random();
        SimplyLinkedList firstlist = new SimplyLinkedList(rnd.nextInt(n),null);
        SimplyLinkedList list = firstlist;
        for (int i = 0; i < n-1; i++) {
            SimplyLinkedList nxt = new SimplyLinkedList(rnd.nextInt(n), null);
            list.tail = nxt;
            list=nxt;
        }
        return firstlist;
    }
    public void show(){
        SimplyLinkedList nxt = this;
        while (nxt.tail != null){
            System.out.println(nxt.head);
            nxt = nxt.tail;
        }
        System.out.println(nxt.head);
    }
    public void add(int k){
        SimplyLinkedList nxt = this;
        SimplyLinkedList aux = nxt.tail ;
        SimplyLinkedList aux2 = new SimplyLinkedList(nxt.head, aux);
        nxt.head = k;
        nxt.tail = aux2;
    }
    public SimplyLinkedList get(int k){
        SimplyLinkedList nxt = this;
        int j = 0;
        while(j<k){
            nxt = nxt.tail;
            j++;
        }
        return nxt;
    }
    public int size(){
        SimplyLinkedList nxt = this;
        int n = 0;
        while (nxt.tail != null){
            n++;
            nxt = nxt.tail;
        }
        n++;
        return n;
    }
    public int remove (int k) {
        SimplyLinkedList nxt = this;
        SimplyLinkedList out = get(k);
        int n = nxt.size();
        //the element to remove is the first element in the list
        if (k == 0) {
            SimplyLinkedList t = nxt.tail;
            nxt.head = t.head;
            nxt.tail = t.tail;
        }
        else if (k != n-1){
            int i = 0;
            while (i < k) {
                nxt = nxt.tail;
                i++;
            }
            SimplyLinkedList l = nxt.tail;
            nxt.head = l.head;
            nxt.tail = l.tail;
        }
        else {
            int i = 0;
            while (i < k-1) {
                nxt = nxt.tail;
                i++;
            }
            nxt.tail = null;
        }
    return out.head;
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
    public static void time(int tries, int n){
        //n is the size of the LinkedList
        //we create a simplylinkedlist of n items
        SimplyLinkedList a = create(n);
        int [] seq = index_seq(tries,n);
        //we create a sequence of k integers - nºop
        //it contains random integers 0 - n
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < tries; i++){
            double n0 = System.nanoTime();
            int r = a.remove(seq[i]);
            double n1 = System.nanoTime();
            double t = (n1-n0);
            a.add(r);
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
    public static void main(String [] args){
        int[] sizes = {100, 200, 400, 800, 1600};
        for (int n : sizes) {
            time(10000, n);
        }
    }
}

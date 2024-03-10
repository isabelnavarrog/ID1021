import java.util.Random;
import java.util.Arrays;
public class doublyll {
    Node head;
    Node tail;
    int size;
    public Node head(){
        return this.head;
    }
    public int size(){
        return this.size;
    }
    public Node tail(){
        return this.tail;
    }
    public doublyll(){
        head = null;
        tail = null;
        size = 0;
    }

    public Node[] elements(){
        Node [] list = new Node[this.size];
        Node nxt = this.head;
        int i = 0;
        while (nxt.pos != null){
            list[i] =nxt;
            nxt = nxt.pos;
            i++;
        }
        list[i]=nxt;
        return list;
    }
    public void addNode(int newvalue) {
        Node newNode = new Node(newvalue);
        if(this.head == null) {
            //Both head and tail will point to newNode
            head = tail = newNode;
            //head's previous will point to null
            head.prev = null;
            //tail's next will point to null, as it is the last node of the list
            tail.pos = null;
        }
        else {
            //newNode will be added after tail such that tail's next will point to newNode
            tail.pos = newNode;
            //newNode's previous will point to tail
            newNode.prev = tail;
            //newNode will become new tail
            tail = newNode;
            //As it is last node, tail's next will point to null
            tail.pos = null;
        }
        this.size++;
    }
    public static doublyll create(int n){
        Random rnd= new Random();
        doublyll firstlist = new doublyll();
        for (int i = 0; i < n; i++){
            firstlist.addNode(rnd.nextInt(n));
        }
        return firstlist;
    }
    public void show(){
        Node nxt = this.head;
        while (nxt.pos != null){
            System.out.println(nxt.value);
            nxt = nxt.pos;
        }
        System.out.println(nxt.value);
    }

    public void add(int k){
        Node nxt = this.head;
        Node first = new Node(k);
        first.pos = nxt;
        nxt.prev = first;
        this.head = first;
        this.size++;
    }
    //faltan los casos extremos k=0 y k = n-1;
    public int remove(int k){
        Node[] l =this.elements();
        Node out = l[k];
        int n = l.length;
        if (k != 0 && k!=(n-1)) {
            Node bef = out.prev;
            Node pos = out.pos;
            bef.pos = pos;
            pos.prev = bef;
        }
        else if (k == 0){
            Node pos = out.pos;
            pos.prev = null;
            this.head = pos;
        }
        else if (k==n-1) {
            Node prev = out.prev;
            prev.pos = null;
            this.tail=prev;
        }
        this.size--;
        return out.value;
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
        //we create a doublylinkedlist of n items
        int [] seq = index_seq(tries,n);
        //we create a sequence of k integers - nºop
        //it contains random integers 0 - n
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int i = 0; i < tries; i++){
            doublyll a = create(n);
            int out_index=seq[i];
            double n0 = System.nanoTime();
            int r = a.remove(out_index);
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
    public static void main(String[] args){
        int[] sizes = {100, 200, 400, 800, 1600};
        for (int n : sizes) {
            time(100_000, n);
        }
    }
}

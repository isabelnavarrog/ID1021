import java.util.Random;

public class doublelinkedl{
    public static class Node {
        Node prev;
        int value;
        Node pos;
        public Node(int item){
            prev=null;
            value = item;
            pos =null;
        }
        public void showNode(){
            if (prev !=null && pos !=null) {
                System.out.printf("El elemento de antes es %d\n", prev.value);
                System.out.printf("El elemento que queremos es %d\n", value);
                System.out.printf("El elemento de después es %d\n", pos.value);
            }
            else if (prev == null){
                System.out.println("es el primer elemento");
                System.out.printf("El elemento que queremos es %d\n", value);
                System.out.printf("El elemento de después es %d\n", pos.value);
            }
            else if (pos== null){
                System.out.printf("El elemento de antes es %d\n", prev.value);
                System.out.printf("El elemento que queremos es %d\n", value);
                System.out.println("es el último elemento");
            }
        }
        public Node prev(){
            return this.prev;
        }
        public int value(){
            return this.value;
        }
        public Node pos(){
            return this.pos;
        }
    }

    Node head;
    int size ;
    public Node head(){
        return this.head;
    }
    public int size(){
        return this.size;
    }
    public doublelinkedl (){
        head = null;
        size = 0;
    }
    public Node[] elements(){
        Node [] list = new Node[this.size];
        Node nxt = this.head;
        for (int i = 0 ; i < list.length; i++){
            list [i] = nxt;
            nxt=nxt.pos;
        }
        return list;
    }
    //The add function adds a Node at the beginning
    public void addNode(int k){
        Node newnode = new Node(k);
        if (head == null){
            head = newnode;
        }
        else {
            Node nxt = head;
            Node newfirst = newnode;
            newfirst.pos = nxt;
            nxt.prev = newfirst;
            head = newfirst;
        }
        this.size++;
    }
//    public int remove(int k){
//        Node[] l =this.elements();
//        Node out = l[k];
//        int n = l.length;
//        if (k != 0 && k!=n-1 ) {
////            Node bef = out.prev;
////            Node pos = out.pos;
//            out.prev.pos = out.pos;
//            out.pos.prev = out.prev;
//        }
//        else if (k == 0){
//            Node pos = out.pos;
//            pos.prev = null;
//            this.head = pos;
//        }
//        else if (k == n-1) {
//            Node prev = out.prev;
//            prev.pos= null;
//        }
//        this.size--;
//        return out.value;
//    }
    public int remove(Node out, int k){
        int n = this.size;
        if (k != 0 && k!=n-1 ) {
            Node bef = out.prev;
            Node pos = out.pos;
            out.prev.pos = out.pos;
            out.pos.prev = out.prev;
        }
        else if (k == 0){
            Node pos = out.pos;
            pos.prev = null;
            this.head = pos;
        }
        else if (k == n-1) {
            Node prev = out.prev;
            prev.pos= null;
        }
        this.size--;
        return out.value;
    }
    public void show(){
        Node nxt = this.head;
        while (nxt.pos != null){
            System.out.println(nxt.value);
            nxt = nxt.pos;
        }
        System.out.println(nxt.value);
    }
    public static doublelinkedl create(int n){
        Random rnd = new Random();
        doublelinkedl l = new doublelinkedl();
        for (int i = 0; i < n; i++){
            l.addNode(rnd.nextInt(n));
        }
        return l;
    }
    //the following function will return an array with the indexes of the elements that should be removed
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
            doublelinkedl a = create(n);
            int out_index=seq[i];
            Node [] elem = a.elements();
            Node out = elem[out_index];
            double n0 = System.nanoTime();
            int r = a.remove(out, out_index);
            double n1 = System.nanoTime();
            double t = (n1-n0);
            a.addNode(r);
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
    public static void main(String[] args){
        int [] sizes ={100, 200, 400, 800, 1600};
        for (int n : sizes){
            time(100_000, n);
        }
    }

}

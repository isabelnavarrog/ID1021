import java.util.Random;

public class simplelinkedl {
    public static class Node {
        int value;
        Node tail;
        public Node(int item){
            value = item;
            tail = null;
        }
        public void showNode(){
            System.out.printf("El elemento que queremos es %d\n", value);
            if (tail !=null)
                System.out.printf("El elemento de después es %d\n",tail.value);
            else
                System.out.println("Es el último elemento de la lista");
        }
        public int value(){
            return this.value;
        }
        public Node tail(){
            return this.tail;
        }
    }
    Node head;
    int size;
    public Node head(){
        return this.head;
    }
    public int size(){
        return this.size;
    }
    public simplelinkedl(){
        head = null;
        size = 0;
    }
    public Node[] elements(){
        Node[] list = new Node[this.size];
        Node nxt = this.head;
        int i = 0;
        while (nxt.tail != null){
            list[i] = nxt;
            nxt = nxt.tail;
            i++;
        }
        list[i]=nxt;
        return list;
    }
    //la función añade un elemento al principio de la lista
    public void addNode(int value){
        Node newnode= new Node(value);
        newnode.tail = this.head;
        this.head = newnode;
        this.size++;
    }
    public int remove(Node out,int k){
        int n = this.size;
        if (k == 0){
            head = head.tail;
        }
        else if (k == n-1){
            Node nxt = this.head;
            while (nxt.tail != out){
                nxt = nxt.tail;
            }
            nxt.tail = null;
        }
        else if (k>0 && k < n - 1){
            Node newtail= out.tail;
            Node nxt = this.head;
            while (nxt.tail != out){
                nxt = nxt.tail;
            }
            nxt.tail = newtail;
        }
        return out.value;
    }
    public void show(){
        Node nxt = this.head;
        while (nxt.tail != null){
            System.out.println(nxt.value);
            nxt = nxt.tail;
        }
        System.out.println(nxt.value);
    }
    public static simplelinkedl create(int n){
        Random rnd = new Random();
        simplelinkedl l = new simplelinkedl();
        for (int i = 0; i < n; i++){
            l.addNode(rnd.nextInt(n));
        }
        return l;
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
        simplelinkedl a = create(n);
        for (int i = 0; i < tries; i++){
            Node [] elem = a.elements();
            int out_index=seq[i];
            Node out = elem[out_index];
            double n0 = System.nanoTime();
            int r = a.remove(out,out_index);
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
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, (sum)/tries, max);
    }
    public static void main (String [] args){
        int [] sizes ={100, 200, 400, 800, 1600};
        for (int n : sizes){
            time(100_000, n);
        }
    }

}

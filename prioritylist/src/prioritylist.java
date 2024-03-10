import java.util.Random;

public class prioritylist<P extends Comparable, T> {
    Node first;
    private class Node{
        P priority;
        T item;
        Node next;
        private Node(P prior, T value, Node nxt){
            priority = prior;
            item = value;
            next = nxt;
        }
        public P priority(){
            return this.priority;
        }
        public T item(){
            return this.item;
        }
        public Node next(){
            return this.next;
        }
        public void showNode(){
            System.out.printf("El priority es %4d", this.priority);
            System.out.printf("El elemento es %4d", this.item);
        }
    }
    public prioritylist(){ //inicializamos con una lista vacia
        first = null;
    }
    public boolean empty(){
        return (first == null);
    }
    public T dequeue(){
        if (first == null) //empty
            return null;
        Node nxt = first;
        first = first.next;
        return nxt.item;
    }
    public void enqueue(P prio,T itm){
        if (this.empty())
            first = new Node(prio, itm, null);
        else {
            Node prev = null;
            Node curr = first;
            while((curr.priority.compareTo(prio)< 0) && (curr.next != null)) {
                prev = curr;
                curr = curr.next;
            }
            if (prev == null) {
                if (curr.priority.compareTo(prio) >= 0)
                    first = new Node(prio, itm, first);
                else
                    first.next =new Node(prio,itm,null);
            }
            else
                //prev.next = new Node(prio, itm ,curr);
                if (curr.priority.compareTo(prio) > 0)
                    prev.next = new Node(prio, itm, curr);
                else if (curr.priority.compareTo(prio)==0)
                    curr.next = new Node(prio, itm, curr.next);
                else
                    curr.next = new Node(prio, itm, null);
        }
    }
    public void show(){
        Node nxt = first;
        while (nxt.next != null){
            System.out.println(nxt.priority);
            System.out.println(nxt.item);
            nxt = nxt.next;
        }
        System.out.println(nxt.priority);
        System.out.println(nxt.item);
    }
    public static prioritylist<Integer,Integer> create(int n){
        Random rnd = new Random();
        prioritylist<Integer, Integer> pq = new prioritylist();
        for (int i = 0;i < n; i++){
            int val = rnd.nextInt();
            pq.enqueue(val, val);
        }
        return pq;
    }
    public static void time(int tries, int n){
        prioritylist <Integer,Integer> pq = create(n);
        Random rnd = new Random();
        double min =Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int k = 0; k < tries; k++){
            int newitm = rnd.nextInt(2*n);
            double t0 = System.nanoTime();
            pq.enqueue(newitm, newitm);
            pq.dequeue();
            double t1 =System.nanoTime();
            double t = (t1-t0);
            if (t< min)
                min = t;
            if (t > max)
                max = t;
            sum+=t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, sum/10_000, max);
    }
    public static void main(String[] args){
       int [] sizes ={100,200,400,800,1600};
       for (int n: sizes){
           time(10_000,n );
       }
    }
}

import java.util.Random;

public class priorityqueuelist <P extends Comparable, T>{
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
        public priorityqueuelist(){ //inicializamos con una lista vacia
            first = null;
        }
        public boolean empty(){
            return (first == null);
        }
        public void enqueue(P prior, T itm) {
            if (first == null) //empty
                first = new Node(prior, itm, null);
            else {
                Node nxt = first;
                first = new Node(prior, itm, nxt);
            }
        }
        public T dequeue() {
            if (this.empty()){
                System.out.println("The list is empty, no items to be removed");
                return null;
            }
            else {
                Node out = first;
                if (out.next == null)
                    return first.item;
                else{
                    Node current = first;
                    Node nxt = current.next;
                    while (nxt.next != null){
                        if (out.priority.compareTo(nxt.priority)>=0){
                            out = nxt;
                        }
                        current = nxt;
                        nxt = nxt.next;
                    }
                    if (out.priority.compareTo(nxt.priority)>=0){
                        out = nxt;
                        current.next = nxt.next;
                    }
                    else{
                        if (nxt.next !=null)
                            current.next = nxt.next;
                        else
                            current.next = null;
                    }
                    return out.item;
                }
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
            pq.dequeue();
            pq.enqueue(newitm, newitm);
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

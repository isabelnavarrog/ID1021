import java.util.Random;

public class quicksortlist {
    Node first;
    Node last;
    public static class Node {

        int value;
        Node tail;
        public Node(int item){
            value = item;
            tail =null;
        }
        public int value(){
            return this.value;
        }
        public Node tail(){
            return this.tail;
        }
        public void showNode(){
            System.out.println(this.value); //me enseña el elemento en sí
            if (this.tail != null)
                System.out.print(this.tail.value); //me enseña el item del siguiente elemento
            else
                System.out.println("ultimo");
        }
    }
    public quicksortlist(){
        first = null;
        last= null;
    }
    // we create the append function to add a list in the end of the list
    public void append(quicksortlist end){
        if (this.last != null)
            this.last.tail = end.first;
        else
            this.last = end.first;
        this.last = end.last;
    }
    //we create the prepend function that adds an element at the front of the list
    public void prepend (quicksortlist start){
        if (start.last != null)
            start.last.tail=this.first;
        if (this.last == null) //the empty list
            this.last = start.last;
        this.first = start.first;
    }
    public void cons (Node nd) {
        if (this.last == null) {
            this.first = nd;
        }
        else {
            this.last.tail = nd;
        }
        this.last = nd;
    }
    public static quicksortlist create (int  n){
        Random rnd = new Random();
        quicksortlist l = new quicksortlist();
        for (int i = 0;i < n; i++){
            l.cons(new Node(rnd.nextInt(2*n)));
        }
        return l;
    }
    public void show (){
        Node nxt = this.first;
        while (nxt != null){
            System.out.println(nxt.value);
            nxt = nxt.tail;
        }
    }

    public Node partition(Node beg, Node end) {
        if (beg == end || beg == null || end == null)
            return beg;

        Node pivot_prev = beg;
        Node curr = beg;
        int pivot = end.value;

        // iterate till one before the end,
        // no need to iterate till the end
        // because end is pivot
        while (beg != end) {
            if (beg.value < pivot) {
                // keep tracks of last modified item
                pivot_prev = curr;
                int temp = curr.value;
                curr.value = beg.value;
                beg.value = temp;
                curr = curr.tail;
            }
            beg = beg.tail;

        }

        // Swap the position of curr i.e.
        // next suitable index and pivot
        int temp = curr.value;
        curr.value = pivot;
        end.value = temp;

        // Return one previous to current
        // because current is now pointing to pivot
        return pivot_prev;
    }
//public Node partition (Node beg, Node end){
//    if (beg == end || beg == null || end == null)
//        return beg;
//    Node pivot = beg;
//    Node nxt = beg.tail;
//    Node now = pivot;
//    while(nxt != end.tail){
//        if(nxt.value <= pivot.value){
//            now = pivot;
//            pivot = pivot.tail;
//            int temp = nxt.value;
//            nxt.value = pivot.value;
//            pivot.value = beg.value;
//            now.value = temp;
//        }
//        nxt = nxt.tail;
//    }
//    return now;
//}
    public void sort(Node head, Node end){
        if(head == null || head == end){
            return;
        }
        Node previous = partition(head, end);
        if(previous == head && previous != null){
            this.sort(previous.tail, end);
        }else if(previous.tail == end && previous != null){
            this.sort(head, previous);
        }else{
            this.sort(head, previous);
            this.sort(previous.tail.tail, end);
        }
    }
    public static void time(int tries, int n){
        Random rnd = new Random();
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int k = 0; k < tries; k++){
            quicksortlist l = create(n);
            double t0 = System.nanoTime();
            l.sort(l.first, l.last);
            double t1 = System.nanoTime();
            double t = (t1-t0);
            if (t < min)
                min = t;
            if (t > max)
                max = t;
            sum+=t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, (sum)/tries, max);
    }

    public static void main(String [] args){
        int [] sizes = {100,200,400,800,1600, 3200};
        for (int s : sizes){
            time(100_000,s);
        }
    }
}

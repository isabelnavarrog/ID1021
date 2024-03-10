import java.util.Random;
public class DoublyLinkedList {
    DoublyLinkedList prev;
    int head;
    DoublyLinkedList tail;

    public DoublyLinkedList(DoublyLinkedList list1,int item, DoublyLinkedList list2) {
        prev = list1;
        head = item;
        tail = list2;
    }

    public DoublyLinkedList prev(){
        return this.prev;
    }
    public int head(){
        return this.head;
    }
    public DoublyLinkedList tail(){
        return this.tail;
    }

    public static DoublyLinkedList create(int n){
        Random rnd = new Random();
        DoublyLinkedList firstlist = new DoublyLinkedList(null,rnd.nextInt(n),null);
        DoublyLinkedList list = firstlist;
        for (int i = 0; i < n-1; i++){
            DoublyLinkedList nxt = new DoublyLinkedList(list, rnd.nextInt(n), null);
            list.tail=nxt;
            list = nxt;
        }
        return firstlist;
    }
    public void show(){
        DoublyLinkedList nxt = this;
        while (nxt.tail != null){
            System.out.println(nxt.head);
            nxt = nxt.tail;
        }
        System.out.println(nxt.head);
    }
    public DoublyLinkedList get(int k){
        DoublyLinkedList nxt = this;
        int j = 0;
        while(j<k){
            nxt = nxt.tail;
            j++;
        }
        return nxt;
    }

    public int size(){
        DoublyLinkedList nxt = this;
        int n = 0;
        while (nxt.tail != null){
            n++;
            nxt = nxt.tail;
        }
        n++;
        return n;
    }
    public void append (DoublyLinkedList b){
        DoublyLinkedList nxt = this;
        while (nxt.tail != null){
            nxt = nxt.tail;
        }
        nxt.tail=b;
        b.prev=nxt;
    }
    public DoublyLinkedList add (int k) {
        DoublyLinkedList l = new DoublyLinkedList(null, k, this);
        this.prev=l;
        return l;
    }
    public int remove (int k){
        DoublyLinkedList nxt = this;
        int n = nxt.size();
        DoublyLinkedList out = nxt.get(k);
        int r = out.head;
        if (k > 0 && k < n-1){
            DoublyLinkedList bef = out.prev;
            DoublyLinkedList aft = out.tail;
            bef.tail=aft;
            aft.prev = bef;
        }
        else if (k == 0){
            DoublyLinkedList aft = out.tail;
            this. head = aft.head;
            this.prev = null;
            this.tail=aft.tail;
       }
        else if (k == n-1){
            DoublyLinkedList bef = out.prev;
            out.prev = null;
            bef.tail=null;
         }
        else { //there are no more elements in the list
            System.out.println("No element can be removed");
            return -1;
        }
       return r;
    }
    public int removeDouble(int k){     /*Removes node in index k and returns its value*/
        DoublyLinkedList item = this.get(k);
        DoublyLinkedList nxt = this;
        if(k == 0){
            if(item.tail == null){
                nxt.head = -1;
            }else{
                DoublyLinkedList aux = nxt.tail;
                nxt.head = aux.head;
                nxt.tail = aux.tail;
                nxt.prev = null;
            }
        }else{
            if(item.tail == null){
                item.prev.tail = null;
            }else{
                DoublyLinkedList bef = item.prev;
                DoublyLinkedList aft = item.tail;
                bef.tail = aft;
                aft.prev = bef;
            }
        }
        return(item.head);
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
        DoublyLinkedList a = create(n);
        int [] seq = index_seq(tries,n);
        //we create a sequence of k integers - nºop
        //it contains random integers 0 - n
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        System.out.println("-------");
        for (int i = 0; i < tries; i++){
            a.show();
            double n0 = System.nanoTime();
            int r = a.removeDouble(seq[i]);
            double n1 = System.nanoTime();
            double t = (n1-n0);
            a=a.add(r);
            System.out.println("------");
            a.show();
            System.out.println("-----");
//            a.append(new DoublyLinkedList(null, r,null));
            if (t < min){
                min = t ;
            }
            if (t > max){
                max = t;
            }
            sum += t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, (sum)/tries, max);
        System.out.println(a.size());
        System.out.println("------");
        a.show();
        System.out.println("------");
    }

    public static void main (String  [ ] args) {
//        int[] sizes = {100, 200, 400, 800, 1600};
//        for (int n : sizes) {
//            time(1_000, n);
//        }
        time(10_000,100);
    }
}

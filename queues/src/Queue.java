public class Queue {
    Node head;
    public class Node{
        public Integer item;
        public Node tail;
        public Node(Integer item, Node list){
            this.item = item;
            this.tail = list;
        }
    }
    public Queue(){
        this.head = null;
    }

    public boolean empty(){
        return (head==null);
    }
    public void add(Integer item){
        this.head = new Node(item, this.head);
    }
    public Integer remove () {
        if (this.head == null)
            return null;
        Node prev = null;
        Node next  = this.head;
        while (next.tail != null){
            prev = next;
            next = next.tail;
        }
        if (prev == null)
            this.head = null;
        else
            prev.tail = null;
        return  next.item;
    }
    public void show(){
        Node nxt = this.head;
        while (nxt.tail != null) {
            System.out.println(nxt.item);
            nxt = nxt.tail;
        }
        System.out.println(nxt.item);
    }
    public static Queue create (int n){
        Queue q = new Queue();
        for (int i = 0; i < n; i++){
            q.add(i);
        }
        return q;
    }

    public static void main(String[] args){
        Queue q = create(10);
        q.show();
    }
}

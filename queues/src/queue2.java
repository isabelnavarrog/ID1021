public class queue2 {
    Node front;
    Node ultimo;

    public class Node {
        int item;
        Node tail;
        public Node(Integer item, Node tail){
            this.item = item;
            this.tail = tail;
        }
    }

    public queue2(){
        front = ultimo = null;
    }

    public void enqueue(Integer item){
        Node next = new Node(item, null);
        if (this.front == null){
            this.front = next;
        }
        if (this.ultimo != null){
            this.ultimo.tail = next;
        }
        this.ultimo = next;
    }
    public int dequeue(){
        if (this.front == null){
            return -1;
        }
        Node out = this.front;
        if (this.front == this.ultimo){
            this.front = this.ultimo = null;
        }
        else{
            this.front = out.tail;
        }
        return out.item;
    }

    public void show () {
        Node next = this.front;
        while (next.tail != null){
            System.out.println(next.item);
            next = next.tail;
        }
        System.out.println(next.item);
    }

    public static queue2 create(int n){
        queue2 newq = new queue2();
        for (int i = 0; i < n; i++){
            newq.enqueue(i);
        }
        return newq;
    }

    public static void main (String[] args){
        queue2 q= new queue2();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.show();
        q.dequeue();
        System.out.println("-----");
        q.show();
    }

}

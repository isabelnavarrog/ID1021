public class priorityintlist {
    Node first;
    private class Node{
        int item;
        Node next;
        private Node(int value, Node nxt){
            item = value;
            next = nxt;
        }
        public int item(){
            return this.item;
        }
        public Node next(){
            return this.next;
        }
    }
    public priorityintlist(){ //inicializamos con una lista vacia
        first = null;
    }
    public boolean empty(){
        return (first == null);
    }

    public void enqueue(int itm){
        if (this.empty()){
            first = new Node(itm, null);
        }
        else {
            Node prev = null;
            Node curr = first;
            while (curr.item <= itm && curr.next != null){
                prev = curr;
                curr = curr.next;
            }
            if (prev == null) //es el elemento mas pequeño
                first = new Node(itm, first);
            else
                if (curr.item>itm)
                    prev.next = new Node(itm ,curr);
                else
                    curr.next = new Node(itm, null);
        }
    }
    public int dequeue(){
        if (first == null) //empty
            return -1000;
        Node nxt = first;
        first = first.next;
        return nxt.item;
    }
    public void show(){
        Node nxt = first;
        while (nxt.next != null){
            System.out.println(nxt.item);
            nxt = nxt.next;
        }
        System.out.println(nxt.item);
    }
    public static void main(String[] args){
        priorityintlist l = new priorityintlist();
        System.out.println(l.empty());
        l.enqueue(4);
        l.enqueue(2);
        l.enqueue(1);
        l.enqueue(3);
        l.enqueue(5);
        l.show();
        l.dequeue();
        System.out.println("....");
        l.show();
    }
}

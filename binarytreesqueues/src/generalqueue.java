//este es el modelo general para una lista
public class generalqueue<Type> {
    Node head;
    private class Node{
        Type item;
        Node tail;
        public Node(Type item, Node list){
            this.item = item;
            this.tail = list;
        }
    }
    public generalqueue(){
        this.head = null;
    }

    public boolean empty(){
        return (head==null);
    }
    public void add(Type itm){
        this.head = new Node(itm, this.head);
    }
    public Type remove () {
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


    public static void main(String[] args){
        generalqueue q = new generalqueue<Integer>() ;
        q.add(3);
        q.add(2);
        q.add(4);
        q.show();
    }
}

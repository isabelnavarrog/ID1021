public class Node {
    Node prev;
    int value;
    Node tail;
    public Node(Node bef, int item, Node aft){
        prev=bef;
        value = item;
        tail =aft;
    }
    public Node prev(){
        return this.prev;
    }
    public int value(){
        return this.value;
    }
    public Node tail(){
        return this.tail;
    }

}

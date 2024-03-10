public class Node {
    Node prev;
    int value;
    Node pos;
    public Node(int item){
        prev=null;
        value = item;
        pos =null;
    }
    public Node prev(){
        return this.prev;
    }
    public int value(){
        return this.value;
    }
    public Node pos(){
        return this.pos;
    }
}

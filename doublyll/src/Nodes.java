public class Nodes {
    int value;
    Nodes tail;
    public Nodes(int item){
        value = item;
        tail =null;
    }
    public int value(){
        return this.value;
    }
    public Nodes tail(){
        return this.tail;
    }
}

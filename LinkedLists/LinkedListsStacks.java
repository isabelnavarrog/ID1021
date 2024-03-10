public class LinkedListsStacks {
    private LinkedLists set;
    private int sp;

    public LinkedListsStacks() {
        this.set = set;
        this.sp = 0;
    }

    public void push (int n){
        LinkedLists a = new LinkedLists(n, null);
        set.append(a);
        sp++;
    }

    public int pop(){
        sp--;
        if (sp < 0){
            System.out.println("Empty stack");
            return -1;
        }
        else{
            return set.remove();
        }

    }

}

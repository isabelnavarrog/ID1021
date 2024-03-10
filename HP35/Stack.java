
public class Stack{
    private int[] set;
    private int sp;

    public Stack(int n) {
        this.set = new int[n];
        this.sp = 0;
    }
    public void push(int n) {
        if(sp==set.length-1) {
            System.out.println("Stack is full");
            return;
        }
        set[sp] = n;
        sp++;
    }

    public int pop() {
        sp--;
        if (sp<0){
            System.out.println("Empty stack");
            return 0;
        }
        int re = this.set[sp];
        return (re);
    }
}
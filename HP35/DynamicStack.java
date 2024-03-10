
 //ONE BY ONE IMPLEMENTATION
public class DynamicStack {
    private int[] set;
    private int sp;

    public DynamicStack() {
        this.set = new int[4]; //we create a small stack
        this.sp = 0;
    }
    //this implementation increases the size of the stack one by one
    public static int[] extend(int[] set){
        int l = set.length;
        int [] extension= new int[l+1];
        for (int i = 0; i < l; i++){
            extension[i]=set[i];
        }
        return extension;
    }
    public void push(int n) {
        if(sp==set.length-1) {
            set=extend(set);
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


//FIXED SIZE INCREMENT
public class DynamicStackFixedSize {
    private int[] set;
    private int sp;
    private int ip;
    public DynamicStack() {
        this.set = new int[4]; //we create a small stack
        this.sp = 0;
    }
    //this implementation increases the size of the stack one by one
    public static int[] extend(int[] set){
        int l = set.length;
        int [] extension= new int[2*l]; //we have decided that the stack should double its size
        for (int i = 0; i < l; i++){
            extension[i]=set[i];
        }
        return extension;
    }
    
    public static int [] reduce(int[] set, int n){
        int [] reduction = new int [n+1];
        for (int j = 0; j < n; j++){
            reduction[j]=set[j];
        }
        return reduction;
    }
    public void push(int n) {
        if(sp==set.length-1) {
            set=extend(set);
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
}*/

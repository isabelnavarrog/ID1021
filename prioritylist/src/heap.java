import java.util.Random;
public class heap {
    Node root;
    private class Node{
        Integer priority;
        Node left;
        Node right;
        Integer numnodes; //number of nodes in the tree
        private Node(Integer value){
            this.priority = value;
            this.left = null;
            this.right = null;
            this.numnodes = 1;
        }
        private void print(Integer n){
            System.out.println(" ".repeat(n) + this.priority);
            if(this.left == null && this.right == null){
                return;
            }
            if(this.left != null){
                this.left.print(n + 4);
            }else{
                System.out.println(" ".repeat(n + 4)+ "----");
            }
            if(this.right != null){
                this.right.print(n+4);
            }else
                System.out.println(" ".repeat(n+4) + "----");
        }
        private void add(Integer newitempr){
            if (newitempr < this.priority) {
                Integer temp = this.priority;
                this.priority = newitempr;
                newitempr = temp;
            }
            this.numnodes++;
            if (this.left == null){
                this.left = new Node(newitempr);
            } else if (this.right == null) {
                this.right = new Node(newitempr);
            } else if (this.right.numnodes < this.left.numnodes) {
                this.right.add(newitempr);
            } else {
                this.left.add(newitempr);
            }
        }
        private Boolean check(){
            Integer pri  = this.priority;
            if(this.left != null){
                if (this.left.priority < pri){
                    return false;
                }
            }
            if(this.right != null && this.right.priority < pri){
                return false;
            }
            Boolean lb = true;
            Boolean rb = true;
            if(this.left != null){
                lb = this.left.check();
            }
            if(this.right != null){
                rb = this.right.check();
            }
            return(rb && lb);
        }
        private Node remove(){
            if (this.numnodes == 0){
                throw new UnsupportedOperationException("empty tree");
            }
            else if (this.numnodes == 1){
                this.priority = null;
                this.right = this.left = null;
                this.numnodes--;
                return null;
            }
            else{
                if (this.left == null){
                    return this.right;
                }
                if (this.right == null) {
                    return this.left;
                }
                if (this.right.priority < this.left.priority) {
                    this.priority = this.right.priority;
                    this.right = this.right.remove();
                }else{
                    this.priority = this.left.priority;
                    this.left = this.left.remove();
                }
                this.numnodes--;
            }
            return this;
        }
        private void swap_r(){
            int temp = this.priority;
            this.priority = this.right.priority;
            this.right.priority = temp;
        }
        private  void swap_l(){
            int temp = this.priority;
            this.priority = this.left.priority;
            this.left.priority = temp;
        }
    }
    public heap(){
        root = null;
    }
    public void enqueue(int value){
        if (root == null)
            root = new Node(value);
        else
            this.root.add(value);
    }
    public static int log2(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }
    public int enqueuedepth(int value){
        if (root == null)
            root = new Node(value);

        else
            this.root.add(value);
        int depth = log2(this.root.numnodes);
        return depth;
    }
    public int dequeue(){
        if (this.root == null){
            throw new UnsupportedOperationException("empty tree");
        }
        else{
            int out =this.root.priority;
            this.root.remove();
            return out;
        }
    }
    public void print() {
        if (this.root == null) {

        } else {
            root.print(4);
        }
    }
    public Boolean check(){
        if(this.root == null){
            return true;
        }
        else {
            return this.root.check();
        }
    }
    //thi
    public int[] get_values (int depth) {
        if (depth == 0) {
            int[] val = {root.priority};
            return val;
        } else {
            Node left = root.left;
            Node right = root.right;
            this.root = left;
            int[] l1 = this.get_values(depth - 1);
            this.root = right;
            int[] l2 = this.get_values(depth - 1);
            int[] val = concatenate(l1, l2);
            return val;
        }
    }

    public static int[] concatenate(int [] array1, int [] array2){
        int aLen = array1.length;
        int bLen = array2.length;
        int[] result = new int[aLen + bLen];
        System.arraycopy(array1, 0, result, 0, aLen);
        System.arraycopy(array2, 0, result, aLen, bLen);
        return result;
    }
    public static int max(int i, int j){
        if (i > j){
            return i;
        }else {
            return j;
        }
    }
    public int depth(){
        if (root == null)
            return 0;
        else{
            if (root.left != null){
                this.root = root.left;
                return 1+ this.depth();
            }
            else{
                this.root = root.right;
                return 1+ this.depth();
            }
        }
    }
    public static heap create(int n){
        heap hp = new heap();
        Random rnd = new Random();
        for (int i = 0; i<n; i++){
            hp.enqueue(rnd.nextInt(n));
        }
        return hp;
    }
    public int push(Integer incr){
        Node nxt = root;
        int newnum = root.priority + incr;
        int depth = 0;
        int max = this.depth();
        while (depth < max) {
            depth++;
            if (nxt.left.priority > nxt.right.priority) {
                if (nxt.left.priority < newnum) {
                    nxt.swap_l();
//                    nxt = nxt.left;
                }
            }else if (nxt.left.priority <= nxt.right.priority) {
                if (nxt.right.priority < newnum) {
                    nxt.swap_r();
//                    nxt = nxt.right;
                }
            } else {
                break;
            }
        }
        return depth;
    }
    public static void time_enq_deq(int tries, int n){
        Random rnd = new Random();
        double min =Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int k = 0; k < tries; k++){
            heap hp = create(n);
            int inc = rnd.nextInt(n);
            double t0 = System.nanoTime();
            int i = hp.push(inc);
            double t1 =System.nanoTime();
            double t = (t1-t0);
            if (t< min)
                min = t;
            if (t > max)
                max = t;
            sum+=t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, sum/10_000, max);
    }
    public static void main(String[] args) {
        int [] sizes ={100,200,400,800,1600};
        for (int n: sizes){
            time_enq_deq(10_000,n );
        }
    }
}

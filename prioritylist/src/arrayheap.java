import java.util.Random;

public class arrayheap {
    Integer[] tree;
    Integer size;

    public arrayheap(int n) {
        tree = new Integer[n];
        size = 0;
    }

    public void enqueue(Integer key) {
        this.tree[size] = key;
        Integer i = size;
        if (size == 0) {
            tree[0] = key;
        } else {
            while (i > 0) {
                if (i % 2 == 0) {
                    if (tree[(i - 2) / 2] > tree[i]) {
                        tree[i] = tree[(i - 2) / 2];
                        tree[(i - 2) / 2] = key;
                        i = (i - 2) / 2;
                    } else {
                        break;
                    }
                } else {
                    if (tree[(i - 1) / 2] > tree[i]) {
                        tree[i] = tree[(i - 1) / 2];
                        i = (i - 1) / 2;
                    } else {
                        break;
                    }
                }
            }
        }
        size++;
    }

    public Integer dequeue() {
        if (tree[0] == null) {
            return null;
        }
        Integer tmp = tree[0];
        tree[0] = tree[size - 1];
        tree[size - 1] = null;
        this.sink();
        size--;
        return tmp;
    }

    public void sink() {
        Integer i = 0;
        Integer p = tree[0];
        while (2 * i + 1 < size - 1) {
            if (tree[2 * i + 2] == null) {
                if (tree[2 * i + 1] < p) {
                    tree[i] = tree[2 * i + 1];
                    tree[2 * i + 1] = p;
                    i = 2 * i + 1;
                } else {
                    break;
                }
            } else {
                if (tree[i] <= tree[2 * i + 1] & tree[i] <= tree[2 * i + 2]) {
                    break;
                } else if (tree[i] > tree[2 * i + 1] & tree[i] <= tree[2 * i + 2]) {
                    tree[i] = tree[2 * i + 1];
                    tree[2 * i + 1] = p;
                    i = 2 * i + 1;
                } else if (tree[i] <= tree[2 * i + 1] & tree[i] > tree[2 * i + 2]) {
                    tree[i] = tree[2 * i + 2];
                    tree[2 * i + 2] = p;
                    i = 2 * i + 2;
                } else {
                    if (tree[2 * i + 1] <= tree[2 * i + 2]) {
                        tree[i] = tree[2 * i + 1];
                        tree[2 * i + 1] = p;
                        i = 2 * i + 1;
                    } else {
                        tree[i] = tree[2 * i + 2];
                        tree[2 * i + 2] = p;
                        i = 2 * i + 2;
                    }
                }
            }
        }
    }

    public void increment(Integer incr) {
        if (tree[0] == null) {
            return;
        }
        tree[0] = tree[0] + incr;
        this.sink();
    }
    public static arrayheap create(int n){
        arrayheap arrayhp = new arrayheap(n);
        Random rnd = new Random();
        for (int i = 0; i<n; i++){
            arrayhp.enqueue(rnd.nextInt(2*n));
        }
        return arrayhp;
    }
    public static void time(int tries, int n){
        arrayheap arrayhp = create(n);
        Random rnd = new Random();
        double min =Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        for (int k = 0; k < tries; k++){
            double t0 = System.nanoTime();
            int r = arrayhp.dequeue();
            arrayhp.enqueue(r);
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
    public static void main(String[] args){
        int [] sizes ={100,200,400,800,1600, 3200};
        for (int n: sizes){
            time(10_000,n );
        }
    }
}

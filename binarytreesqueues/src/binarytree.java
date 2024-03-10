
import java.util.Iterator;
import java.util.Random;
public class binarytree implements Iterable<Integer> {
    @Override
    public Iterator<Integer> iterator() { //solo tengo que redefinir itertor
        return new TreeIterator();
    }

    //entonces en vez de devolverme lo que me devolvería el método normal de java lo que
    //va a devolverme el TreeIterator que definimos después
    public class Node {
        public Integer key;
        public Integer value;
        public Node left, right;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            this.right = this.left = null;
        }

        public void add(Integer key, Integer value) {
            if (this.key == key) {
                this.value = value;
                return;
            }
            if (this.key > key) {
                if (this.left != null)
                    this.left.add(key, value);
                else
                    this.left = new Node(key, value);
                return;
            }
            if (this.key < key) {
                if (this.right != null)
                    this.right.add(key, value);
                else
                    this.right = new Node(key, value);
                return;
            }
        }

        public void print() {
            if (left != null)
                left.print();
            System.out.println(" key: " + key + "\tvalue: " + value);
            if (right != null)
                right.print();
        }
    }

    public class TreeIterator implements Iterator<Integer> {
        private Node next;
        private generalqueue<Node> list;

        public TreeIterator() {
            next = root;
            list = new generalqueue<Node>();
            while (root != null) {
                list.add(root);
                root = root.left;
            }
            next = root;
        }

        public Node nxt() {
            return next;
        }

        public generalqueue<Node> queue() {
            return list;
        }

        @Override
        public boolean hasNext() {
            return !list.empty();
        }

        @Override
        public Integer next() {
            while (next != null) {
                list.add(next);
                next = next.left;
            }
            Node next = list.remove();
            if (next.right != null) {
                while (next.right != null) {
                    list.add(next.right);
                    next.right = next.right.left;
                }
            }
            return next.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    Node root;

    public binarytree() {
        root = null;
    }

    public void add(Integer key, Integer value) {
        if (root == null) {
            root = new Node(key, value);
        } else {
            root.add(key, value);
        }
    }

    public int lookup(Integer key) {
        Node cur = this.root;
        while (cur != null) {
            if (cur.key == key)
                return cur.value;
            if (cur.key < key)
                cur = cur.right;
            else
                cur = cur.left;
        }
        return -10000;
    }

    public static int[] seq(int n) {
        Random rnd = new Random();
        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = rnd.nextInt(10 * n);
        }
        return seq;
    }

    public static binarytree construct(int n) {
        binarytree t = new binarytree();
        int[] index_seq = seq(n);
        for (int i = 0; i < n; i++) {
            t.add(index_seq[i], i);
        }
        return t;
    }


    public static void time(int tries, int n) {
        double min = Double.POSITIVE_INFINITY;
        double max = 0;
        double sum = 0;
        Random rnd = new Random();
        binarytree tree = construct(n);
        for (int i = 0; i < tries; i++) {
            int look = rnd.nextInt(10 * n);
            double n0 = System.nanoTime();
            tree.lookup(look);
            double n1 = System.nanoTime();
            double t = (n1 - n0);
            if (t < min) {
                min = t;
            }
            if (t > max) {
                max = t;
            }
            sum += t;
        }
        System.out.printf("n: %6d \t min: %.2fns\t avg: %.2fns\t max: %.2fns\n", n, min, (sum) / (tries), max);
    }


    public static void main (String[] args) {
        binarytree tree = new binarytree();

        tree.add(5, 105);
        tree.add(2, 102);
        tree.add(7, 107);
        tree.add(1, 101);
        tree.add(8, 108);
        tree.add(6, 106);
        tree.add(3, 103);
        for (int i : tree)
            System.out.println("next value " + i);
    }
}



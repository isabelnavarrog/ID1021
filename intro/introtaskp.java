public class introtaskI {
    public static void main() {
        int[] given = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int sum = 45;
        for (int i = 0; i < 10; i++) {
            long t0 = System.nanoTime();
            long t1 = System.nanoTime();
            System.out.println(" resolution " + (t1 - t0) + " nanoseconds");
        }
    }
}

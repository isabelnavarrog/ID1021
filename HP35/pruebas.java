public class pruebas {
        static Stack stack200= new Stack(200);
        static Stack stack400= new Stack(400);
        static Stack stack800= new Stack(800);
        static Stack stack1600= new Stack(1600);

        public static void time(Stack stack ,int n){
            double n0=System.nanoTime();
            for (int i=0; i < n ; i++){
                stack.push(1);
            }
            double n1 = System.nanoTime();
            double t = n1-n0;
            System.out.println(t);
        }

        public static void  main(String[] args){
            time(stack200, 199);
            time(stack400, 399);
            time(stack800, 799);
            time(stack1600, 1599);


        }

}

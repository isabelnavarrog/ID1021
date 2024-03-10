public class dynamicqueue<Type> {
    Type [] queue;
    int first  = 0;
    int last = 0;
    int size = 10;

    int numberelements = 0;
    //int size = 100; ponemos como parámetro la longitud para los benchmarks
    public dynamicqueue(){
        this.queue = (Type[]) new Object[this.size];
    }
    //returns is the array is empty or not
    public boolean empty(){
        return (numberelements == 0);
//        return ((first == last)&&(queue[first]==null));
    }

    public void add(Type item){
        if (this.empty()){
            queue[first] = item;
            last++;
            numberelements++;
        }
        else if (first < last){
            queue[last] = item;
            if (last < size-1)
                last ++;
            else
                last = 0;
            numberelements++;
        }
        else if (last <= first){
            if (last == first && !this.empty()){
                queue = this.duplicate();
                first = 0;
                size = queue.length;
                int sum = 0;
                while (queue[sum] != null){
                    sum++;
                }
                last = sum;
                numberelements = sum;
                queue[last] = item;
                last++;
                numberelements++;
            }
            else{
                queue[last] = item;
                last ++;
                numberelements++;
            }
        }

    }
    public Type[] duplicate(){
        int n = size;
        Type[] newqueue =(Type []) new Object[2*n];
        if (first >= last) {
            for (int i = first; i < size; i++) {
                newqueue[i - first] = queue[i];
            }
            for (int j = 0; j < last; j++) {
                newqueue[j + this.size] = queue[j];
            }
            for (int k = n; k < 2 * n; k++) {
                newqueue[k] = null;
            }
        }
        else{
            for (int j = first; j < last; j++) {
                newqueue[j - first] = queue[j];
            }
            for (int k = last; k < 2 * n; k++) {
                newqueue[k] = null;
            }
        }
        return newqueue;
    }
    public Type [] reduce(){
        int n = size ;
        Type[] reduced = (Type[]) new Object[n/2];
        if (first >= last) {
            for (int i = first; i < size; i++) {
                reduced[i - first] = queue[i];
            }
            for (int j = 0; j < last; j++) {
                reduced[j + this.size] = queue[j];
            }
            for (int k = n; k < n/2; k++) {
                reduced[k] = null;
            }
        }
        else{
            for (int j = first; j < last; j++) {
                reduced[j - first] = queue[j];
            }
            for (int k = last; k < n/2; k++) {
                reduced[k] = null;
            }
        }
        return reduced;
    }
    public Type remove (){
        if (numberelements < size/3) {
            queue = this.reduce();
            size = queue.length;
            first = 0;
            int sum = 0;
            while (queue[sum] != null) {
                sum++;
            }
            last = sum;
            numberelements = sum;
        }
        if (this.empty()){
            System.out.println("No items to be removed");
            numberelements--;
            return null;
        }
        else if (first < size -1 ){
            Type out = queue[first];
            queue[first] = null;
            first++;
            if (first == last){
                numberelements--;
                return null; //the list is empty
            }
            else {
                numberelements--;
                return out;
            }
        }
        else {
            numberelements--;
            Type out = queue[first];
            queue[first]= null;
            first = 0;
            if (first == last){
                return null;
            }
            else {
                return out;
            }
        }
    }

    public void show(){
        for (int i = 0; i < this.size; i++){
            System.out.println(this.queue[i]);
        }
    }
    public static void main (String[] args){
        dynamicqueue<Integer> q = new dynamicqueue<Integer>();
        for (int i =  0; i < 10 ; i++){
            q.add(i);
        }
        q.show();
        System.out.println("-----");
        q.add(10);
        q.show();
        q.remove();
        q.add(11);
        System.out.println("-----");
        q.show();
        System.out.println("-----");
        q.remove();
        q.remove();
        q.remove();
        q.remove();
        q.remove();
        q.remove();
        q.remove();
        q.remove();
        q.remove();
        q.remove();
        q.show();
        q.add(2);
        System.out.println("----");
        q.show();

    }
}

import java.nio.IntBuffer;

public class QueueArray<Type> {
    Type [] queue;
    int first  = 0;
    int last = 0;
    int size = 10;
    //int size = 100; ponemos como parámetro la longitud para los benchmarks
    public QueueArray(){
        this.queue = (Type[]) new Object[this.size];
    }
    //returns is the array is empty or not
    public boolean empty(){
        return ((first == last)&&(queue[first]==null));
    }

    public void add(Type item){
        if (this.empty()){
            queue[first] = item;
            last++;
        }
        else if (first < last){
            queue[last] = item;
            if (last < size-1)
                last ++;
            else
                last = 0;
        }
        else if (last < first){
                queue[last] = item;
                last ++;
                if (last == first && !this.empty()){
                    System.out.println("No more elements in the list");
                }
        }

    }
    public Type remove (){
        if (this.empty()){
            System.out.println("No items to be removed");
            return null;
        }
        else if (first < size -1 ){
            Type out = queue[first];
            queue[first] = null;
            first++;
            if (first == last){
                return null; //the list is empty
            }
            else {
                return out;
            }
        }
        else {
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
        QueueArray<Integer> q = new QueueArray();
        System.out.println(q.empty());
        for (int i = 0; i < 4;i++){
            q.add(i);
        }
        q.add(4);
        q.add(5);
        q.add(6);
        q.add(7);
        q.add(8);
        q.add(9);
        q.show();
        System.out.println("-------");
        q.add(10);
        System.out.println(q.remove());
        q.remove();
        q.show();
        q.add(10);
        q.show();
    }
}

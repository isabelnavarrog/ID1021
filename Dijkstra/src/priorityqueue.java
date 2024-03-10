public class priorityqueue<Integer extends Comparable, City> {
    Node first;
    private class Node{
        entry item;

        Integer priority;
        Node next;
        private Node(entry value, Node nxt){
            priority = (Integer) value.distance;
            item = value;
            next = nxt;
        }
        public Integer priority(){
            return this.priority;
        }
        public entry item(){
            return this.item;
        }
        public Node next(){
            return this.next;
        }
    }
    public priorityqueue(){ //inicializamos con una lista vacia
        first = null;
    }
    public boolean empty(){
        return (first == null);
    }
    public entry dequeue(){
        if (first == null) //empty
            return null;
        Node nxt = first;
        first = first.next;
        return nxt.item;
    }
    public int enqueue(entry itm) {
        int position = 0;
        Integer prio = (Integer) itm.distance;
        if (this.empty()) {
            first = new Node(itm, null);
            position = 0;
        }
        else{
            Node prev = null;
            Node curr = first;
            while ((curr.priority.compareTo(prio) < 0) && (curr.next != null)) {
                prev = curr;
                curr = curr.next;
                position++;
            }
            if (prev == null) {
                if (curr.priority.compareTo(prio) >= 0)
                    first = new Node(itm, first);
                else
                    first.next = new Node(itm, null);
            } else
                //prev.next = new Node(prio, itm ,curr);
                if (curr.priority.compareTo(prio) > 0)
                    prev.next = new Node(itm, curr);
                else if (curr.priority.compareTo(prio) == 0)
                    curr.next = new Node(itm, curr.next);
                else
                    curr.next = new Node(itm, null);
        }
        return position;
    }
    public entry get (String cityname){
        Node current = first;
        while ((!current.next.item.city.name.equals(cityname)) && (current.next !=null)){
            current = current.next;
        }
        return current.item;
    }
    public boolean isfound (String cityname){
        Node current = first;
        if(this.empty())
            return false;
        while (current.next != null){
            if (current.next.item.city.name.equals(cityname))
                return true;
            current = current.next;
        }
        return false;
    }
    public static void main(String [] args){
        Map m = new Map("trains.csv");
        entry source = new entry(m.lookup("Stockholm"), 0, null);
        priorityqueue pq = new priorityqueue();
        pq.enqueue(source);
        for (int i = 0; i < source.city.neighbours.length;i++){
            if ((source.city.neighbours[i] != null))
                System.out.println(pq.enqueue(new entry(source.city.neighbours[i].destination,source.city.neighbours[i].distance,source.city)));
        }
        System.out.println(pq.isfound("Malmö"));
    }
}

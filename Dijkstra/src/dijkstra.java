import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class dijkstra {
    priorityqueue unprocessed;
    entry [] processed;
    public dijkstra(){
        unprocessed = new priorityqueue();
        processed = new entry[541];
    }
    private Integer hash(String name) {
        int hash = 7;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash*31 % 541) + name.charAt(i);
        }
        return hash % 541;
    }
    public Integer relocate(Integer index, String cityname) {
        Integer initial = index;
        while (index < processed.length) {
            if (processed[index] == null) {
                break;
            }
            if (processed[index].city.name.equals(cityname)) {
                break;
            }
            index++;
        }
        if (index == processed.length) {
            index = 0;
        }
        while (index < initial) {
            if (processed[index] == null) {
                break;
            }
            if (processed[index].city.name == cityname) {
                break;
            }
            index++;
        }
        if (index == initial) {
            System.out.println("no space for this city");
        }
        return index;
    }
    public Integer position(String cityname) {
        Integer initial = hash(cityname);
        if (processed[initial] != null) {
            if (processed[initial].city.name.equals(cityname)) {
                return initial;
            } else {
                return relocate(initial, cityname);
            }
        }
        else {
            return initial;
        }
    }
    public Integer shortest (City origin, City destination){
        entry source = new entry(origin,0, null);
        unprocessed.enqueue(source);
        while(!unprocessed.empty()){
            entry currentnode = unprocessed.dequeue();
            Connection[] nb = currentnode.city.neighbours;
            int i = 0;
            while( i < nb.length){
                if (nb[i]!=null) {
                    if (processed[position(nb[i].destination.name)]!=null){
                    }
                    else if (!unprocessed.isfound(nb[i].destination.name)) {
                        unprocessed.enqueue(new entry(nb[i].destination, currentnode.distance+nb[i].distance, currentnode.city));
                    } else {
                        if (unprocessed.get(nb[i].destination.name).distance > currentnode.distance + nb[i].distance)
                            unprocessed.get(nb[i].destination.name).distance = currentnode.distance + nb[i].distance;
                    }
                }
                i++;
            }
            processed[position(currentnode.city.name)] = currentnode;
            if (currentnode.city.name.equals(destination))
                break;
        }
        return processed[hash(destination.name)].distance;
    }
    public void dijskstra (City origin){
        entry source = new entry(origin,0, null);
        unprocessed.enqueue(source);
        while(!unprocessed.empty()){
            entry currentnode = unprocessed.dequeue();
            Connection[] nb = currentnode.city.neighbours;
            int i = 0;
            while( i < nb.length){
                if (nb[i]!=null) {
                    if (processed[position(nb[i].destination.name)]!=null){
                    }
                    else if (!unprocessed.isfound(nb[i].destination.name)) {
                        unprocessed.enqueue(new entry(nb[i].destination, currentnode.distance+nb[i].distance, currentnode.city));
                    } else {
                        if (unprocessed.get(nb[i].destination.name).distance > currentnode.distance + nb[i].distance)
                            unprocessed.get(nb[i].destination.name).distance = currentnode.distance + nb[i].distance;
                    }
                }
                i++;
            }
            processed[position(currentnode.city.name)] = currentnode;
            if (currentnode.predecessor!=null)
                System.out.println("The shortest path to " + currentnode.city.name + " is " + currentnode.distance + " coming from " + currentnode.predecessor.name);
            else
                System.out.println("The shortest path to " + currentnode.city.name + " is " + currentnode.distance);

        }
    }
    public static void main(String[] args) {
        Map map = new Map("trains.csv");
        double min  = Double.POSITIVE_INFINITY;
        double max = 0 ;
        double avg = 0 ;
        for (int i = 0; i < 1000; i++){
            dijkstra path = new dijkstra();
            long t0 = System.nanoTime();
            path.dijskstra(map.lookup("Stockholm"));
            long t1 = System.nanoTime();
            long time = t1-t0;
            if (time < min)
                min  = time;
            if (time > max)
                max = time;
            avg +=time ;
        }
        System.out.println("The minimum execution time is " + min);
        System.out.println("\\n.....\\n");
        System.out.println("The maximum execution time is " + max);
        System.out.println("\\n.....\\n");
        System.out.println("The average execution time is " + avg/1000);
        System.out.println("\\n.....\\n");
//        try (BufferedReader br = new BufferedReder(new FileReader("benchmalmo.csv"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                dijkstra path = new dijkstra();
//                String[] arg = line.split(",");
//                String from = arg[0];
//                String to = arg[1];
//                long t0 = System.nanoTime();
//                Integer dist = path.shortest(map.lookup(from), map.lookup(to));
//                long time = (System.nanoTime() - t0)/1_000_000 ;
//                System.out.println("The shorest path from " + from + " to " + to + " is \n");
//                System.out.println("shorest: " + dist + " min (" + time + " ms)");
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}


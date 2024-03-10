import java.io.*;

public class Paths {
    City [] path;
    int sp;
    public Paths(){
        path = new City[54];
        sp = 0;
    }
    private Integer shortest (City from, City to, Integer max){
        if (max < 0)
            return null;
        if (from.equals(to))
            return 0;
        for (int i = 0; i < sp; i++) {
            if (path[i] == from)
                return null;
        }
        path[sp++] = from;
        Integer shrt = null;
        for (int i = 0; i < from.neighbours.length; i++) {
            if (from.neighbours[i] !=null) {
                Connection connection = from.neighbours[i];
                Integer dist_btw = shortest(connection.destination, to, max - connection.distance);
                if ((dist_btw != null) && ((shrt == null) || (shrt > dist_btw + connection.distance)))
                    shrt = dist_btw + connection.distance;
            }
        }
        path[sp--]=null;
        return shrt;
    }
    private Integer shortest_impr(City from, City to, Integer max){
        if (max < 0)
            return null;
        if (from.equals(to))
            return 0;
        for (int i = 0; i < sp; i++) {
            if (path[i] == from)
                return null;
        }
        path[sp++] = from;
        Integer shrt = null;
        for (int i = 0; i < from.neighbours.length; i++) {
            if (from.neighbours[i] !=null) {
                Connection connection = from.neighbours[i];
                Integer dist_btw = shortest_impr(connection.destination, to, max - connection.distance);
                if ((dist_btw != null) && ((shrt == null) || (shrt > dist_btw + connection.distance)))
                    shrt = dist_btw + connection.distance;
                if ((shrt != null) && (max > shrt))
                    max = shrt;
            }
        }
        path[sp--]=null;
        return shrt;
    }
    public static void main(String[] args) {
        Map map = new Map("trains.csv");
        try (BufferedReader br = new BufferedReader(new FileReader("benchmalmo.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Paths pat = new Paths();
                String[] arg = line.split(",");
                String from = arg[0];
                String to = arg[1];
                long t0 = System.nanoTime();
//                Integer dist = pat.shortest(map.lookup(from), map.lookup(to), 100_000);
                Integer dist = pat.shortest_impr(map.lookup(from), map.lookup(to), 1_500);
                long time = (System.nanoTime() - t0)/1_000_000 ;
                System.out.println("The shorest path from " + from + " to " + to + " is \n");
                System.out.println("shorest: " + dist + " min (" + time + " ms)");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

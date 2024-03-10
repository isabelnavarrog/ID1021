import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Naive {

    public static Integer shortest(City from, City to, Integer max) {
        if (max < 0)
            return null;
        if (from.equals(to))
            return 0;
        Integer shrt = null;
        for (int i = 0; i < from.neighbours.length; i++) {
            if (from.neighbours[i] != null) {
                Connection connection = from.neighbours[i];
                String nam =connection.destination.name;
                Integer dist_btw = shortest(connection.destination, to, max - connection.distance);
                if ((dist_btw != null) && ((shrt == null) || (shrt > dist_btw + connection.distance)))
                    shrt = dist_btw + connection.distance;
//                if ((shrt != null) && (max > shrt))
//                    max = shrt;
            }
        }
        return shrt;
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("benchmark.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Map map = new Map("trains.csv");
                String[] arg = line.split(",");
                String from = arg[0];
                String to = arg[1];
                Integer max = Integer.valueOf(arg[2]);
                long t0 = System.nanoTime();
                Integer dist = shortest(map.lookup(from), map.lookup(to), max);
                long time = (System.nanoTime() - t0) / 1_000_000;
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

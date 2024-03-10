import java.io.BufferedReader;
import java.io.FileReader;

public class Map {
    private City[] cities;
    private final int mod = 541;
    public Map(String file) {
        cities =  new City[mod];
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                City first = this.lookup(row[0]);
                City last = this.lookup (row[1]);
                Integer distance = Integer.valueOf(row[2]);
                first.add_news(last, distance);
                last.add_news(first, distance);
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found or corrupt");
        }
    }
    private Integer hash(String name) {
        int hash = 7;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash*31 % mod) + name.charAt(i);
        }
        return hash % mod;
    }
    public Integer relocate(Integer index, String cityname) {
        Integer initial = index;
        while (index < cities.length) {
            if (cities[index] == null) {
                break;
            }
            if (cities[index].name.equals(cityname)) {
                break;
            }
            index++;
        }
         if (index == cities.length) {
            index = 0;
        }
        while (index < initial) {
            if (cities[index] == null) {
                break;
            }
            if (cities[index].name == cityname) {
                break;
            }
            index++;
        }
        if (index == initial) {
            System.out.println("no space for this city");
        }
        return index;
    }

    public City lookup(String cityname) {
        Integer index = hash(cityname);
        if (cities[index] == null){
            City city = new City(cityname);
            cities[index] = city;
        }else if (!cities[index].name.equals(cityname)){
                index = this.relocate(index,cityname);
                if (cities[index]==null) {
                    City city = new City(cityname);
                    cities[index] = city;
                }
        }
        return cities[index];
    }
    public static void main(String[] args){
        Map m = new Map("trains.csv");
        City c = m.lookup("Umeå");
        for (int i = 0; i<c.neighbours.length; i++)
            if (c.neighbours[i] != null)
                System.out.println(c.neighbours[i].destination.name);

    }
}

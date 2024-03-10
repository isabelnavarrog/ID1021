public class entry {
    City city;
    Integer distance;
    City predecessor;
    public entry(City name, Integer dist,City pred){
        city = name;
        distance = dist;
        predecessor = pred;
    }
    public City city(){
        return this.city;
    }
    public Integer distance(){
        return this.distance;
    }
    public City predecessor(){
        return this.predecessor;
    }
}

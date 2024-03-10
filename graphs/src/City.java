public class City {
    String name;
    Connection[] neighbours;
    public City(String city){
        name = city;
        neighbours = new Connection[4];
        //cada ciudad tiene a lo sumo cuatro conexiones
    }
    public void add_news(City dest, Integer dist){
        Connection con = new Connection(dest, dist);
        for (int i = 0; i <neighbours.length; i++){
            if(neighbours[i] == null){
                neighbours[i] = con;
                return;
            }
        }
    }
}

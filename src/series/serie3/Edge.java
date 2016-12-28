package series.serie3;

/**
 * Created by João Gameiro on 26/12/2016.
 */
public class Edge {
    Vertex adjacent;
    double weight;
    Edge next;


    public Edge(Vertex adjacent, double weight) {
        this.adjacent = adjacent;
        this.weight = weight;

    }

    public Edge(Vertex adjacent, double weight,Edge next){
        this.adjacent = adjacent;
        this.weight = weight;
        this.next=next;
    }
}

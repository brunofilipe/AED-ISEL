package series.serie3;

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

package series.serie3;

public class Edge {
    Vertex dest;
    double weight;
    Edge next;
    public Vertex source;


    public Edge(Vertex source,Vertex dest, double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;

    }

    public Edge(Vertex dest, double weight, Edge next){
        this.dest = dest;
        this.weight = weight;
        this.next=next;
    }
}

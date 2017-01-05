package series.serie3.Ex3;

public class Edge {
    public Vertex dest;
    public double weight;
    public Edge next;
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

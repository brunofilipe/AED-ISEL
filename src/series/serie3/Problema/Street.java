package series.serie3.Problema;

public class Street {
    public Crossing dest;
    public double weight;
    public Street next;
    public Crossing source;
    public boolean isVisited ;


    public Street(Crossing source, Crossing dest, double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
        isVisited = false;

    }

    public Street(Crossing dest, double weight, Street next){
        this.dest = dest;
        this.weight = weight;
        this.next=next;
    }
}

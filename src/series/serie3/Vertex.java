package series.serie3;


public class Vertex {
    int id;
    Edge list;
    boolean visited;

    public Vertex(int id){
        this.id=id;
    }

    public Vertex(int id, Edge list){
        this.id = id;
        this.list = list;
    }

}

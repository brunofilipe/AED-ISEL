package series.serie3.Ex3;


public class Vertex {
    public int id;
    public Edge list;


    public Vertex(int id){
        this.id=id;
    }

    public Vertex(int id, Edge list){
        this.id = id;
        this.list = list;
    }

}

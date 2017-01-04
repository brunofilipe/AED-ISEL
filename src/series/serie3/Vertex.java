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

    public void addEdge(Vertex v) {
        if(list==null){
            list = new Edge(v,1);
            return;
        }
        Edge e = list;

        if(e.dest.equals(v))
            return;

        while(e.next!=null) {
            e = e.next;
            if(e.dest.equals(v))
                return;
        }

        e.next = new Edge (v,1);
    }
}

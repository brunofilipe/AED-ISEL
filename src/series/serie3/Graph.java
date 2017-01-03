package series.serie3;


public class Graph extends DisjointSets {
    Vertex[]v ;
    PriorityQueue<Edge,Double>queue;
    int count;

    public Graph(Vertex[]v){
        this.v = v;
        queue = new PriorityQueue<>(Double::compareTo);
    }


    @Override
    public void makeSet(int p) {
        queue.add(v[p].list,v[p].list.weight);
        ++count;
    }

    @Override
    public Edge findSet(int p) {
       return queue.search(v[p].list   );
    }

    @Override
    public void union(Edge p, Edge q) {

    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public int parent(int i) {
        return 0;
    }

    public Vertex[] kruskal(Vertex[] graph) {
        for (int i = 0; i < graph.length; i++) {
            makeSet(i);
        }
        
        return null;

    }
}

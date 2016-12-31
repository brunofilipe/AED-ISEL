package series.serie3;

public class EdgeInAnMST extends DisjointSets{
    @Override
    public void makeSet(int p) {

    }

    @Override
    public int findSet(int p) {
        return 0;
    }

    @Override
    public void union(Edge p, Edge q) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int parent(int i) {
        return 0;
    }



    public static int isEdgeInAnMST(Vertex[] graph, int origId, int destId) {
        Vertex[]graphinMst = kruskal(graph);
    }

    private  Vertex[] kruskal(Vertex[] graph) {
        Vertex[]vertices ;
        for (int i = 0; i < graph.length; i++) {
            makeSet(i);
        }
    }
}

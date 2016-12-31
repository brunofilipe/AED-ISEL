package series.serie3;


public class Set extends DisjointSets {
    protected int id[];
    public Set(int N) {
        id = new int[N];
        makeSets();
    }
    @Override
    public void makeSet(int p) {
        id[p] = p;
    }

    @Override
    public int findSet(int p) {
        return id[ p ];
    }

    @Override
    public void union(Edge p, Edge q) {
        for (int i= 0; i < size(); ++i)
            if ( id[i] == p) id[i] = q;
    }

    @Override
    public int size() {return id.length;}

    @Override
    public int parent(int i) {return id[i];}


}

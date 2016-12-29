package series.serie3;


public abstract class DisjointSets {


    public abstract void makeSet( int p );
    public abstract int findSet( int p );
    public abstract void union( int p, int q );
    public abstract int size( );
    public abstract int parent(int i);

    public boolean isConnected( int p, int q ) {
        return findSet( p ) == findSet( q );
    }
    public boolean connect( int p, int q ) {
        int rootP = findSet(p), rootQ = findSet(q);
        if ( rootP == rootQ ) return false;
        union( rootP, rootQ );
        return true;
    }
    public void makeSets()
    { for (int i=0; i < size(); ++i) makeSet( i ); }

    public boolean isRoot( int i ) { return parent(i) == i; }

}

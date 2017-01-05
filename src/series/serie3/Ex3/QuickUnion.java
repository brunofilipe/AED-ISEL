package series.serie3.Ex3;

public class QuickUnion extends ArrayDisjointSets {
	public QuickUnion( int N ) {
		super( N );
	}

	public int findSet( int p ) {
		int root = p;
		while( root != id[root] ) // !isRoot
			root = id[root];
		return root;
	}

	public void union( int p, int q ) { id[p]= q; }
}


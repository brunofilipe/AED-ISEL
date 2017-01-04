package series.serie3;

public class WQUPathCompressionHalving extends  WeightedQuickUnion {
	public WQUPathCompressionHalving(int N) { super(N);	}

	public int findSet(int p) {
		int root;
		for (root= p; root != id[root]; root = id[root]) 
			id[root] = id[ id[root] ];
		return root;
	}
}

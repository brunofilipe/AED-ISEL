package series.serie3;

import java.util.Arrays;

public class WeightedQuickUnion extends QuickUnion {
	protected int sz[];
	protected WeightedQuickUnion( int N ){
		super(N);
		sz = new int [N];
		for (int i= 0; i < N; ++i ) sz[i] = 1;
	}

	public void union(int p, int q) {
	  if (sz[p] < sz[q]) weightedUnion (p, q); 
	  else weightedUnion(q, p);
	}

	private void weightedUnion (int p, int q) {
		super.union( p, q ); 
		sz[q]+= sz[p];
	}

	public String toString() { return super.toString()+ Arrays.toString(sz);}

}



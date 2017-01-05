package series.serie3.Ex3;

import java.util.Arrays;

public abstract class ArrayDisjointSets extends DisjointSets {
	  protected int id[];
	  public ArrayDisjointSets(int N ) {
		  id= new int [N];
		  makeSets();
	  }
	  public void makeSet(int p)     { id[p] = p;        }
	  public final int size( )       { return id.length; }
      public int parent(int i)       { return id[i];     }
	  public String toString() { return Arrays.toString(id);}
}


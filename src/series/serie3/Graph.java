package series.serie3;

import java.util.Comparator;

/**
 * Created by Bruno on 29/12/2016.
 */
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
       return queue.search(v[p].list);
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

    private Vertex[] kruskal(Vertex[] graph) {
        Comparator<Double> cmp = (d1,d2)->{
         if(d1<d2)return 1;
         else if (d1>d2) return -1;
          return 0;
         };
        PriorityQueue<Edge,Double> queue = new PriorityQueue<Edge, Double>(cmp);
        for (int i = 0; i < graph.length; i++) {
            makeSet(i);
        }
        return null;

    }
}

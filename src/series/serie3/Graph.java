package series.serie3;


import java.util.*;
import java.util.PriorityQueue;

public class Graph  {

    public static boolean isEdgeInAnMST(Vertex[]graph,int origId,int destId) {
        DisjointSets set = new WQUPathCompressionHalving(graph.length);
        EdgeComparator cmp = new EdgeComparator(origId, destId);
        java.util.PriorityQueue<Edge> pq = new PriorityQueue<>(cmp);
        java.util.ArrayList<Edge> A = new ArrayList<>();
        for (int i = 0; i < graph.length; ++i) {
            pq.add(graph[i].list);
        }
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int u = e.source.id, v = e.dest.id;
            if (!set.isConnected(u, v)) {
                set.union(u, v);
                A.add(e);
            }
            if (set.isConnected(u, v) && u == origId && v == destId) {
                return true;
            }
        }
            return false;

    }

    private static class EdgeComparator implements Comparator< Edge>{
        private int origId;
        private int destId;

        public EdgeComparator(int origId,int destId){
            this.origId = origId;
            this.destId = destId;
        }

        @Override
        public int compare(Edge o1, Edge o2) {
           if(o1.weight>o2.weight){
               return 1;
           }
            if(o1.weight<o2.weight){
                return -1;
            }
            if(o1.source.id == origId && o1.dest.id == destId || o2.source.id == origId && o2.dest.id == destId){
                return -1;
            }
            return 0;
        }
    }

}

package series.serie3;


import java.util.*;
import java.util.PriorityQueue;

public class Graph  {

    public static boolean isEdgeInAnMST(Vertex[]graph,int origId,int destId) {
        DisjointSets set = new QuickUnion(graph.length);
        EdgeComparator cmp = new EdgeComparator(origId, destId);
        //java.util.PriorityQueue<Edge> pq = new PriorityQueue<>(cmp);
        series.serie3.PriorityQueueV2<Edge,Double>pe = new series.serie3.PriorityQueueV2<>(cmp);
        java.util.ArrayList<Edge> A = new ArrayList<>();
        for (int i = 0; i < graph.length; ++i) {
            while (graph[i].list != null){
                pe.add(graph[i].list,graph[i].list.weight);
                graph[i].list = graph[i].list.next;
            }

        }
        while (!pe.isEmpty()) {
            Edge e = pe.poll();
            int u = e.source.id, v = e.dest.id;
            if (!set.isConnected(u, v)) {
                set.union(u, v);
                A.add(e);
                if (u == origId && v == destId || v == origId && u == destId) {
                    //return true;
                }
            }
        }
        return false;

    }

    private static class EdgeComparator implements Comparator< Double>{
        private int origId;
        private int destId;

        public EdgeComparator(int origId,int destId){
            this.origId = origId;
            this.destId = destId;
        }

        @Override
        public int compare(Double o1, Double o2) {
           if(o1>o2){
               return 1;
           }
            if(o1<o2){
                return -1;
            }
            if(o1== origId && o1 == destId || o2 == origId && o2 == destId){
                return 1;
            }
            return 0;
        }
    }

}

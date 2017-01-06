package series.serie3.Ex3;


import java.util.*;

public class Graph  {

    public static boolean isEdgeInAnMST(Vertex[]graph,int origId,int destId) {
        DisjointSets set = new QuickUnion(graph.length);
        EdgeComparator cmp = new EdgeComparator(origId, destId);
        PriorityQueue<Edge,Double> pe = new PriorityQueue<>(cmp);
        java.util.ArrayList<Edge> A = new ArrayList<>();
        for (Vertex aGraph : graph) {
            while (aGraph.list != null) {
                pe.add(aGraph.list, aGraph.list.weight);
                aGraph.list = aGraph.list.next;
            }

        }
        while (!pe.isEmpty()) {
            Edge e = pe.poll();
            int u = e.source.id, v = e.dest.id;
            if (!set.isConnected(u, v)) {
                set.union(u, v);
                A.add(e);
                if (u == origId && v == destId || v == origId && u == destId) {
                    return true;
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
               return -1;
           }
            if(o1<o2){
                return 1;
            }
            if(o1== origId && o1 == destId || o2 == origId && o2 == destId){
                return 1;
            }
            return 0;
        }
    }

}

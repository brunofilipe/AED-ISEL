package series.serie3;


import java.util.*;
import java.util.PriorityQueue;

public class Graph  {

    public static int isEdgeInAnMST(Vertex[]graph,int origId,int destId) {
        DisjointSets set = new WQUPathCompressionHalving(graph.length);
        java.util.PriorityQueue<Edge> pq = new java.util.PriorityQueue<Edge>(new ) {
            @Override
            public int compare(Edge o1, Edge o2) {

            }
        });
        for(int i = 0; i < graph.length;++i) {
            pq.add(graph[i].list);
        }
        while ( !pq.isEmpty() ) {
            Edge e = pq.poll();
            int u = e.source.id, v = e.dest.id;
            if ( !set.isConnected( u,  v)) {
                set.union(u, v);
            }
            if(set.isConnected(u,v) && u == origId && v == destId){
                return 1;
            }
        }
        return -1;

    }

    private class EdgeComparator implements Comparator< Edge>{

        private Vertex[]graph;
        private int origId;
        private int destId;


        @Override
        public int compare(Edge o1, Edge o2) {
            return 0;
        }
    }

}

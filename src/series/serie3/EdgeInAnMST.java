package series.serie3;

public class EdgeInAnMST {

    public static int isEdgeInAnMST(Vertex [] graph, int origId, int destId){

        Vertex [] graphMST = putGraphInMST(graph);

        for (int i = 0; i < graphMST.length; i++) {
            if (graphMST[i].id == origId) {
                Edge list = graphMST[i].list;
                while (list != null) {
                    if (graph[i].list.adjacent.id == destId)
                        return 1;
                    list = list.next;
                }
            }
        }
        return 0;
    }

    private static Vertex[] putGraphInMST(Vertex[] graph) {
        Vertex [] newGraph;
        boolean [] visited;
        int i=0;
        while(true){


        break;

        }


        return graph;
    }
}

package series.serie3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class Ex3Tests {

   private Vertex[]graph;

    @Before
    public void createMst(){
        MST mst = new MST();
        try {
            graph = mst.readFile("grafo.gr");
        } catch (IOException ignored) {}
    }

    @Test
    public void checkIfIsEdgeInAnMST(){
        boolean isEdge = Graph.isEdgeInAnMST(graph,1,0);
        Assert.assertTrue(isEdge);
    }

    @Test
    public void checkIfIsntEdgeInAnMST(){
        boolean isntEdge = Graph.isEdgeInAnMST(graph,6,5);
        Assert.assertFalse(isntEdge);
    }
}

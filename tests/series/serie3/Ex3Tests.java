package series.serie3;

import org.junit.Test;

import java.io.IOException;

public class Ex3Tests {

    @Test
    public void createGraph(){
        MST mst = new MST();
        try {
            Vertex []graph = mst.readFile("grafo.gr");
        } catch (IOException ignored) {}
        int i = 0;

    }
}

package series.serie3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MST {

    public Vertex[] readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Vertex[]graph = new Vertex[0];
        String line = br.readLine();
        while (line!=null){
            if(line.charAt(0) == 'p'){
                graph = new Vertex[line.charAt(5) - '0'];
            }
            else if(line.charAt(0) == 'a'){
              putInVertex(graph,line.charAt(2)-'0',line);
            }
            line = br.readLine();
        }
        return graph;
    }

    public void putInVertex(Vertex[]graph,int idx,String line){
        if(graph[idx] == null){
            graph[idx] = new Vertex(line.charAt(2)-'0');
            graph[idx].list= new Edge(graph[idx],checkIfExists(line.charAt(4)-'0',graph),line.charAt(6)-'0');
        }
        else {
            Edge toInsert = new Edge(graph[idx],checkIfExists(line.charAt(4) - '0',graph), line.charAt(6) - '0');
            Edge n = graph[idx].list;
            while (n.next != null) {n = n.next;}
            n.next = toInsert;
        }
    }

    public Vertex checkIfExists(int id,Vertex[]graph){
        for (int i = 0;i < graph.length; ++i){
            if(graph[i]!=null && graph[i].id== id){
                return graph[i];
            }
        }
        return new Vertex(id);
    }

}

package series.serie3;

import series.serie3.Ex3.Edge;
import series.serie3.Ex3.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class MST {

    public Vertex[] readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Vertex[]graph = new Vertex[0];
        String line = br.readLine();
        while (line!=null){
            String[]text = line.split(" ");
            if(Objects.equals(text[0], "p")){
                graph = new Vertex[Integer.parseInt(text[2])];
            }
            else if(Objects.equals(text[0], "a")){
              putInVertex(graph,Integer.parseInt(text[1]),text);
            }
            line = br.readLine();
        }
        return graph;
    }

    public void putInVertex(Vertex[]graph,int idx,String []line){
        if(graph[idx] == null){
            graph[idx] = new Vertex(Integer.parseInt(line[1]));
            graph[idx].list= new Edge(graph[idx],checkIfExists(Integer.parseInt(line[2]),graph),Double.parseDouble(line[3]));
        }
        else {
            Edge toInsert = new Edge(graph[idx],checkIfExists(Integer.parseInt(line[2]),graph),Double.parseDouble(line[3]));
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

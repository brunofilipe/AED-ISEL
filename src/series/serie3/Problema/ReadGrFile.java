package series.serie3.Problema;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class ReadGrFile {
    public static Crossing[] readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Crossing[]graph = new Crossing[0];
        String line = br.readLine();
        while (line!=null){
            String[]text = line.split(" ");
            if(Objects.equals(text[0], "p")){
                graph = new Crossing[Integer.parseInt(text[2])];
            }
            else if(Objects.equals(text[0], "a")){
                putInCross(graph,Integer.parseInt(text[1]),text);
            }
            line = br.readLine();
        }
        linkGraph(graph);
        return graph;
    }

    public static void putInCross(Crossing[]graph, int idx, String []line){
        if(graph[idx] == null){
            graph[idx] = new Crossing(Integer.parseInt(line[1]));
            graph[idx].list= new Street(graph[idx],checkIfExists(Integer.parseInt(line[2]),graph),Double.parseDouble(line[3]));
        }
        else {
            Street toInsert = new Street(graph[idx],checkIfExists(Integer.parseInt(line[2]),graph),Double.parseDouble(line[3]));
            Street n = graph[idx].list;
            while (n.next != null) {n = n.next;}
            n.next = toInsert;
        }
    }

    public static Crossing checkIfExists(int id,Crossing[]graph){
        if(graph[id]!=null) return graph[id];
        return new Crossing(id);
    }

    public static void linkGraph(Crossing[]graph){
        for (Crossing aGraph : graph) {
            Street iter = aGraph.list;
            while (iter != null) {
                iter.dest = checkIfExists(iter.dest.id, graph);
                iter = iter.next;
            }
        }
    }
}


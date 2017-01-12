package series.serie3.Problema;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class ReadSFile {

    public  static int [] readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int idx = 0;
        String line = br.readLine();
        int [] path = new int[0];
        while (line!=null){
            String[]text = line.split(" ");
            if(text[0].equals("k"))
                path = new int[Integer.parseInt(text[1])];
            else {
                path[idx++] = Integer.parseInt(text[0]);
            }
            line = br.readLine();
        }
        return path;
    }

}

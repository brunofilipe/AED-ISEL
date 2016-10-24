package series.serie1;

import java.io.*;
import java.util.Comparator;


public class MaiorNrOcorrencias {

    private static int nrWords;
    private static File [] files = new File[3];

    public static void main(String[] args) throws IOException {
        init(args);
        String[] read = readFiles(files);
        orderFiles(read);
    }



    public static void init(String[] args) throws IOException {
        if(Integer.parseInt(args[0]) == 0) throw new IllegalArgumentException();
        nrWords = Integer.parseInt(args[0]);
        int j = 0;
        for (int i = 2; i < args.length; i++) {
            files[j] = new File ("N"+(i-1)+".txt");
            ++j;
        }
    }

    public  static String[] readFiles(File[]a) throws IOException {
        String[]toReturn = new String[3 * a[0].getLength()];
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            BufferedReader reader = new BufferedReader(new FileReader(a[i].getFileName()));
            while (reader.ready()){
                toReturn[j] = reader.readLine();
                ++j;
            }
        }
        return toReturn;
    }


    public static void orderFiles(String[]st) throws IOException {
       Comparator<String>cmp = (cmp1, cmp2) -> cmp1.equals(cmp2) ? 1 :-1;
       Heap.buildMinHeap(st,st.length,cmp);
        int aux = 2;
        return;
    }

    public static void outFile(){

    }

}

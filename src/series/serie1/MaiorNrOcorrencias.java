package series.serie1;

import java.io.*;
import java.util.Comparator;


public class MaiorNrOcorrencias {

    private static int nrWords;
    private static File [] files = new File[3];
    private static BufferedWriter writer;
    private static Comparator<File>cmp;


    public static void main(String[] args) throws IOException {
        init(args);
        //orderFiles(files);
        //checkForEquals(files);

    }

    public static void init(String[] args) throws IOException {
        if(Integer.parseInt(args[0]) == 0) throw new IllegalArgumentException();
        cmp = (cmp1, cmp2) -> cmp1.getWord().compareTo(cmp2.getWord());
        nrWords = Integer.parseInt(args[0]);
        writer = new BufferedWriter(new FileWriter(args[1]));
        int j = 0;
        for (int i = 2; i < args.length; i++) {
            files[j] = new File ("N"+(i-1)+".txt");
            ++j;
        }

    }
/*
    private static void checkForEquals(File[] files) throws IOException {
        int idx = 0,count = 0;
        int[]counter = new int[files[0].getLength()];
        for (int i = 0; i < files.length; i++){
            while (files[i].hasNextLine()){
                String word = files[i].getReader().readLine();
                if( word.equals(files[i].getWord())){
                    counter[idx] = ++count;
                }
                else{
                    files[i].setWord(word);
                    counter[idx++] = count;
                    count = 0;
                }
            }
        }
    }
*/
   /* public static void orderFiles(File[]a) throws IOException {
        Heap.buildMinHeap(a, a.length, cmp);
        Object[]
        for (int i = files.length; i > 0; ) {

        }
    }
*/
    public static void outFile(){
    }

}

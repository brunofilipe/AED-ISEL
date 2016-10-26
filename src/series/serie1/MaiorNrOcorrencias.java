package series.serie1;

import java.io.*;
import java.util.Comparator;


public class MaiorNrOcorrencias {

    private static int nrWords;
    private static File [] files = new File[3];
    static int nf = 3;
    private static BufferedWriter writer;
    private static Comparator<File>cmp;
    private static Comparator<Word>word;


    public static void main(String[] args) throws IOException {
        init(args);
        //orderFiles(files);
        //checkForEquals(files);
        test(files);
    }

    public static void init(String[] args) throws IOException {
        if(Integer.parseInt(args[0]) == 0) throw new IllegalArgumentException();
        cmp = (cmp1, cmp2) -> cmp1.getfWord().compareTo(cmp2.getfWord());
        word = (cmp1, cmp2) -> cmp1.getCount() - cmp2.getCount();
        nrWords = Integer.parseInt(args[0]);
        writer = new BufferedWriter(new FileWriter(args[1]));
        int j = 0;
        for (int i = 2; i < args.length; i++) {
            files[j] = new File (args[i]);
            ++j;
        }
    }

    private static void test (File[]fArray) throws IOException {
        int wordCounter=1;
        Heap.buildMinHeap(fArray,fArray.length,cmp);
        String s = fArray[0].nextWord();
        Heap.minHeapify(fArray, fArray.length, 0, cmp );

         while (nf != 0) {
                if(fArray[0].getfWord().equals(s)){
                    ++wordCounter;
                }
                else {


                    wordCounter=1;
                }
             s = fArray[0].nextWord();
             Heap.minHeapify(fArray,nf, 0, cmp );

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

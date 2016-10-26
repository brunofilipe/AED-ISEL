package series.serie1;

import java.io.*;
import java.util.Comparator;


public class MaiorNrOcorrencias {

    private static int nrWords;
    private static File [] files;
    private static Word[] words;
    private static int nf ;
    private static BufferedWriter writer;
    private static Comparator<File>cmp;
    private static Comparator<Word>wcmp;


    public static void main(String[] args) throws IOException {
        init(args);
        test(files);
        sortWords(words);
        writeToOutput(words);
    }

    private static void sortWords(Word[] words) {
        Heap.heapSort(words,words.length, wcmp);
    }

    private static void writeToOutput(Word[] words) throws IOException {
        for (int i = 0; i < words.length; i++) {
            writer.write(words[i].getWordName());
            writer.newLine();
        }
        writer.close();
    }

    public static void init(String[] args) throws IOException {
        if(Integer.parseInt(args[0]) == 0) throw new IllegalArgumentException();
        cmp = (cmp1, cmp2) -> cmp1.getfWord().compareTo(cmp2.getfWord());
        wcmp = (cmp1, cmp2) -> cmp1.getCount() - cmp2.getCount();
        nrWords = Integer.parseInt(args[0]);
        files = new File[args.length - 2];
        words = new Word[nrWords];
        nf = files.length;
        writer = new BufferedWriter(new FileWriter(args[1]));
        int j = 0;
        for (int i = 2; i < args.length; i++) {
            files[j] = new File (args[i]);
            ++j;
        }
    }

    //TODO verificar se o words chegou ao fim e organizar o gajo pra poder inserir novas palavras
    private static void test (File[]fArray) throws IOException {
        int wordCounter=1;
        int widx = 0;
        Heap.buildMinHeap(fArray,fArray.length,cmp);
        String s = fArray[0].nextWord();
        if(fArray[0].getfWord() == null){
            swap(fArray,0,nf);
            --nf;
            Heap.minHeapify(fArray,0,nf,cmp);
        }
        else Heap.minHeapify(fArray, 0,fArray.length, cmp );
        while (nf != 0) {

            if(fArray[0].getfWord().equals(s)){
                ++wordCounter;
            }
            else {
                Word w = new Word(fArray[0].getfWord(),wordCounter);
                words[widx++] = w;
                wordCounter=1;
            }
             s = fArray[0].nextWord();
            if(fArray[0].getfWord() == null) {
                swap(fArray, 0, nf);
                --nf;
            }
             Heap.minHeapify(fArray,0,nf, cmp );
            }
        }

    private static void swap(File[] fArray, int i, int nf) {
        File f = fArray[i];
        fArray[i] = fArray[nf-1];
        fArray[nf-1] = f;
    }



}

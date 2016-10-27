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
        countWords(files);
        writeToOutput(words);
    }

    private static void writeToOutput(Word[] words) throws IOException {
        for (int i = 0; i < words.length; i++) {
            writer.write(words[i].getWordName() + "  ocorre: " + words[i].getCount() + " vezes");
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

    private static void countWords(File[] f) throws IOException {
        int wordCounter=0;
        int widx=0;
        Heap.buildMinHeap(f,nf,cmp);
        Word w= new Word(f[0].getfWord(),wordCounter);

        while(nf!=0){

            while(w.getWordName().equals(f[0].getfWord())){
                w.increment();
                f[0].setWord(f[0].getReader().readLine());
                checkEndofFile(f[0]);
            }
            if(widx==nrWords){
                Heap.buildMinHeap(words,nrWords,wcmp);
                if(words[0].getCount() < w.getCount())
                    words[0]=w;
                Heap.minHeapify(words,0,nrWords,wcmp);
            }
            else words[widx++] = w;
            w = new Word (f[0].getfWord(),0);

        }
    }

    private static void checkEndofFile(File f) throws IOException {
       if(f.getfWord()==null){
           f.getReader().close();
           swap(files, 0, nf);
           --nf;
       }
        Heap.minHeapify(files,0,nf,cmp);
    }

    private static void swap(File[] fArray, int i, int nf) {
        File f = fArray[i];
        fArray[i] = fArray[nf-1];
        fArray[nf-1] = f;
    }
}
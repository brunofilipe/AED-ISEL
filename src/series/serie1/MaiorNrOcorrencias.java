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
        test1(files);
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

    private static void test1 (File [] f) throws IOException{
        int wordCounter=1;
        int widx=0;
        Heap.buildMinHeap(f,f.length,cmp);
        /*f[0].setNextWord();
        String snextWord = f[0].getnextWord();
        String scurrWord = f[0].getfWord();
        */
        Word w = new Word(f[0].getfWord(),wordCounter);
        while(nf!=0){
            String aux = f[0].getnextWord();
            if(f[0].nextWord() !=null){
                if(f[0].getfWord().equals(f[0].getnextWord())){
                    w.increment();
                    f[0].setWord(f[0].getnextWord());
                    //f[0].nextWord();
                }
                else  {
                    //if((w.getWordName().equals(aux)))
                      //  w.increment();
                    f[0].setWord(f[0].getnextWord());
                    f[0].nextWord();
                    //f[0].setWord(scurrWord);
                   // f[0].setNextWord();
                    Heap.minHeapify(f,0,nf,cmp);
                    //f[0].nextWord();
                   // f[0].setNextWord();
                   // snextWord = f[0].getnextWord();
                   // scurrWord = f[0].getfWord();
                    if(!(w.getWordName().equals(f[0].getfWord()))) {
                        words[widx++] = w;
                        wordCounter=1;
                        w=new Word(f[0].getfWord(),wordCounter);
                    }
                }
            }
            else {
                if((w.getWordName().equals(aux)))
                    w.increment();
                f[0].getReader().close();
                swap(f, 0, nf);
                --nf;
                if(nf==0) {
                    words[widx] = w;
                    break;
                }
                Heap.minHeapify(f, 0, nf, cmp);
                //snextWord = f[0].getnextWord();
                //scurrWord = f[0].getfWord();
                if(!(w.getWordName().equals(f[0].getfWord()))) {
                    words[widx++] = w;
                    wordCounter = 1;
                    w = new Word(f[0].getfWord(), wordCounter);
                }
            }
        }
    }

    private static void swap(File[] fArray, int i, int nf) {
        File f = fArray[i];
        fArray[i] = fArray[nf-1];
        fArray[nf-1] = f;
    }
}

package series.serie1;

import java.io.*;
import java.util.Comparator;


public class MaiorNrOcorrencias {

    private static int nrWords;              //representa as K palavras
    private static File [] files;            //array de Ficheiros
    private static Word[] words;             //array de palavras e ocorrencias
    private static int nf ;                  //numero de ficheiros legiveis
    private static BufferedWriter writer;
    private static Comparator<File>cmp;      //comparador de ficheiros
    private static Comparator<Word>wcmp;     //comparador de palavras


    public static void main(String[] args) throws IOException {
        //10 palavras por ficheiro -141.0
        //100 palavras por ficheiro - 159.0
        //1000 palavras por ficheiro - 264.0
        //10000 palavras por ficheiro - 341.0
        //100 000 palavras por ficheiro - 356.0
        //createFiles(10000, 3); //method to test the problem, creates files with given dimension
        double start = System.currentTimeMillis();
        init(args);
        countWords(files);
        writeToOutput(words);
        double end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void createFiles(int size, int numberOfFiles) throws IOException {
        for (int i = 2, aux = size; i <= numberOfFiles; i++, aux = size) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("test"+i+".txt"));
            BufferedReader reader = new BufferedReader(new FileReader("f"+i+".txt"));
            while (aux-- > 0) {
                writer.write(reader.readLine());
                writer.newLine();
            } writer.close(); reader.close();
        }
    }

    private static void writeToOutput(Word[] words) throws IOException {
        for (int i = 0; i < words.length; i++) {
            writer.write(words[i].getWordName() + "  ocorre:" + words[i].getCount() + " vezes");
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
        //nf = files.length;
        writer = new BufferedWriter(new FileWriter(args[1]));
        int j = 0;
        for (int i = 2; i < args.length; i++) {
            files[j] = new File (args[i]);
            if(files[j].getfWord()!=null) ++nf;
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
                Heap.minHeapify(words,0,wcmp,nrWords);
            }
            else words[widx++] = w;
            w = new Word (f[0].getfWord(),0);

        }
    }

    private static void checkEndofFile(File f) throws IOException {
       if(f.getfWord()==null){
           f.getReader().close();
           swap(files, 0, --nf);
       }
        Heap.minHeapify(files,0,cmp,nf);
    }

    private static void swap(File[] fArray, int i, int nf) {
        File f = fArray[i];
        fArray[i] = fArray[nf];
        fArray[nf] = f;
    }
}

package series.serie1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class File {

    public BufferedReader getReader() {
        return reader;
    }

    private BufferedReader reader;

    public void setWord(String word) {
        this.fWord = word;
    }

    private String fWord;

    public String getFileName() {
        return fileName;
    }

    private String fileName;

    public File(String fileName) throws java.io.IOException {
        this.fileName = fileName;
        reader = new BufferedReader(new FileReader(fileName));
        fWord = reader.readLine();
    }

    public String getfWord() { return fWord; }
    public String nextWord() throws IOException { String  w = fWord; fWord= reader.readLine(); return w; }


    public boolean hasNextLine() throws IOException { return reader.ready(); }

    public int getLength() throws IOException {
        int counter = 0;
        while (reader.ready()){
            reader.readLine();
            ++counter;
        }
        reader.close();
        return counter;
    }
    @Override
    public String toString(){
        return fileName;
    }

}

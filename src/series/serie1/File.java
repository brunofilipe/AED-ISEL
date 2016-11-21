package series.serie1;

import java.io.BufferedReader;
import java.io.FileReader;

public class File {


    private String fileName;
    private BufferedReader reader;
    private String fWord;

    public File(String fileName) throws java.io.IOException {
        this.fileName = fileName;
        reader = new BufferedReader(new FileReader(fileName));
        fWord = reader.readLine();
    }
    public String getfWord() { return fWord; }

    public void setWord(String word) {
        this.fWord = word;
    }

    public BufferedReader getReader() {
        return reader;
    }

    @Override
    public String toString(){
        return fileName;
    }

}

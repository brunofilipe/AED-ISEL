package series.serie1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class File {

    public BufferedReader getReader() {
        return reader;
    }

    private BufferedReader reader;
    private String word;

    public String getFileName() {
        return fileName;
    }

    private String fileName;

    public File(String fileName) throws java.io.IOException {
        this.fileName = fileName;
        reader = new BufferedReader(new FileReader(fileName));
    }


    public String getWord() { return word; }

   public void setWord(String word) { this.word = word; }

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

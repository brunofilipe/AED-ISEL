package series.serie1;


public class Word {

    private String wordName;
    private int count;

    public Word(String wordName, int count){
        this.wordName = wordName;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getWordName() {
        return wordName;
    }

    public void increment(){
        this.count++;
    }
}

package series.serie3;


public class Fragment {

    private char id;
    private int counter=1;
    Fragment [] child = new Fragment[4];
    boolean isTerminal=false;


    public Fragment(char id){
        this.id=id;
    }

    public int getCounter(){
        return counter;
    }

    public char getId(){
        return id;
    }

    public void incCounter(){
         ++counter;
    }


}

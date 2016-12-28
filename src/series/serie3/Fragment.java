package series.serie3;


public class Fragment {

    private char id;
    Fragment [] child = new Fragment[4];
    boolean isTerminal=false;


    public Fragment(char id){
        this.id=id;
    }

    public char getId(){
        return id;
    }

}

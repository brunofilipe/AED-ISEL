package series.serie3.Ex2;


public class Fragment {

    private char id;
    public Fragment [] child = new Fragment[4];
    boolean isTerminal=false;


    public Fragment(char id){
        this.id=id;
    }

    public char getId(){
        return id;
    }

}

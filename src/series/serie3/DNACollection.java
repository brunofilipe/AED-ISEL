package series.serie3;

/**
 * Created by João Gameiro on 21/12/2016.
 */
public class DNACollection {

    String [] collection;

    public DNACollection(int length){
        //collection = new String [] {"A","C","T","G"};
        collection = new String [length] ;
    }

    public void add(String fragment){
        switch (fragment.charAt(0)){
            case 'A':
                checkIfNull(0,fragment);
                break;
            case 'C':
                checkIfNull(1,fragment);
                break;
            case 'T':
                checkIfNull(2,fragment);
                break;
            case 'G':
                checkIfNull(3,fragment);
                break;
        }
    }

    private void checkIfNull(int i, String fragment) {
        if(collection[i] != null)
            collection[i]+= fragment;
        collection[i] = fragment;
    }

    public int prefixCount(String p){return 0;}


}
class Main{
    public static void main(String[] args) {
        DNACollection dna = new DNACollection(4);
        dna.add("A");
        dna.add("AC");
        dna.add("TG");
        dna.add("CG");
        dna.add("G");
        for (int i = 0; i <4 ; i++) {
            System.out.println(dna.collection[i]);
        }
    }
}


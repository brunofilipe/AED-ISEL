package series.serie3;

/**
 * Created by João Gameiro on 21/12/2016.
 */
public class DNACollection {

    TreeNode <Fragment> [] collection;

    public DNACollection(int length){
        collection = new TreeNode [length] ;
    }

    public void add(String fragment){
       String aux = fragment.substring(1);
        switch (fragment.charAt(0)) {
            case 'A':
                checkIfNull(0, fragment);
                break;
            case 'C':
                checkIfNull(1, fragment);
                break;
            case 'T':
                checkIfNull(2, fragment);
                break;
            case 'G':
                checkIfNull(3, fragment);
                break;
        }
    }


    private void checkIfNull(int i, String fragment) {
        while(collection[i].item != null){
            if (collection[i].item.child == null)
               // collection[i].item.child = new Fragment();
            collection[i].item = collection[i].item.child;
        }
        if(collection[i].item == null)
            collection[i].item = new Fragment(fragment);
    }

    public int prefixCount(String p){return 0;}


}
class Main{
    public static void main(String[] args) {
        DNACollection dna = new DNACollection(4);
        String abc = "abc";
        String aux= abc.substring(1);
        System.out.println(aux);
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


package series.serie3;


import series.serie2.Problema.HashTable;

public class DNACollection {

    Fragment[] collection;
    HashTable<Character,Integer> map;
    public DNACollection(int length){
        map = new HashTable<>(4,2);
        map.put('A',0);
        map.put('C',1);
        map.put('T',2);
        map.put('G',3);
        collection = new Fragment[length] ;
    }

    public void add(String fragment){
        int charIdx = 0;
        put(collection,charIdx,fragment);
    }

    private void put(Fragment []fragment, int charIdx, String p) {
        if (charIdx>=p.length())return;
        int idx = map.get(p.charAt(charIdx));
        if(fragment[idx] == null){
            fragment[idx] = new Fragment(p.charAt(charIdx));
        }
        put(fragment[idx].child,++charIdx,p);
    }

    public int prefixCount(String p) {
        int idx = map.get(p.charAt(0));
        return 0;
    }


}
class Main{
    public static void main(String[] args) {
        DNACollection dna = new DNACollection(4);
        dna.add("A");
        dna.add("AC");
        dna.add("ACG");
        dna.add("TG");
        dna.add("CG");
        dna.add("G");

    }
}


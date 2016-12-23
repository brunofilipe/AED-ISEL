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
        put(collection,fragment,charIdx);
    }

    private void put(Fragment []fragment, String p ,int charIdx ) {

        if (charIdx>=p.length())return;

        int idx = map.get(p.charAt(charIdx));
        if(fragment[idx] == null){
            fragment[idx] = new Fragment(p.charAt(charIdx));
        }else {
            if(p.length()-charIdx>1)
                fragment[idx].isTerminal=false;
        }
        if(p.length()-charIdx == 1 && isEmpty(fragment[idx].child))
            fragment[idx].isTerminal = true;

        put(fragment[idx].child,p,++charIdx);
    }

    public int prefixCount(String p) {

        int idx = map.get(p.charAt(0));
        checkTerminals(collection[idx]);
        return 0;
    }

    public void checkTerminals(Fragment f){
        int count=0;
        if(f == null)
            return;
        if(f.isTerminal)
            ++count;

    }

    private boolean isEmpty(Fragment[]fragments){
        for (Fragment fragment : fragments) {
            if (fragment != null) return false;
        }
        return true;
    }


}
class Main{
    public static void main(String[] args) {
        DNACollection dna = new DNACollection(4);
        dna.add("AC");
        dna.add("ACG");
        dna.add("ACT");
        dna.add("ACA");
        dna.add("AAG");


        int a = dna.prefixCount("A");

    }
}


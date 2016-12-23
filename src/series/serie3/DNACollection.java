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
        //int idx = map.get(fragment.charAt(charIdx));
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
        int count = 0;
        int idx = map.get(p.charAt(0));
        if (collection[idx] != null){
            count = collection[idx].getCounter();
            //toEnd(collection,idx);
        }

        return count;
    }

    private int toEnd(Fragment[] fragment,int idx) {

        return 0;
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


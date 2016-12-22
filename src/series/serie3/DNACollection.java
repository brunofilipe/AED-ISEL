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
        int count = 0;
        int idx = map.get(p.charAt(0));
        if (collection[idx] != null){
            count = toEnd(collection,idx);
        }

        return count;
    }

    private int toEnd(Fragment[] fragment,int idx) {

        if(isEmpty(fragment[idx].child)) return toEnd(fragment,++idx);
        int i = 0;
        while (fragment[idx].child[i]== null){
            ++i;
        }
        return toEnd(fragment[idx].child,idx);
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
        dna.add("A");
        dna.add("C");
        dna.add("T");
        dna.add("G");
        dna.add("AA");
        dna.add("ACG");
        dna.add("ACT");
        dna.prefixCount("A");
    }
}


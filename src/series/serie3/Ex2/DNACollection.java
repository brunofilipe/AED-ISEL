package series.serie3.Ex2;


import series.serie3.Ex3.HashTable;

public class DNACollection {

    public Fragment[] collection;
    public HashTable<Character,Integer> map;
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
        }
        else {
            if(p.length()-charIdx>1)
                fragment[idx].isTerminal=false;
        }
        if(p.length()-charIdx == 1 && isEmpty(fragment[idx].child))
            fragment[idx].isTerminal = true;

        put(fragment[idx].child,p,++charIdx);
    }

    public int prefixCount(String p) {
        int idx = map.get(p.charAt(0));
        return checkTerminals(collection,idx,0);
    }

    public int checkTerminals(Fragment []f, int pos,int i) {
        if(i==f.length)i=0;
        if (pos == f.length) return 0;
        while (f[pos]==null){
            ++pos;
            if (pos == f.length)return 0;
        }
        if(isEmpty(f[pos].child)) return checkTerminals(f, ++pos,i) + 1 ;
        while (f[pos].child[i] == null) {
            ++i;
            if(i==f.length)
                return checkTerminals(f,++pos,0);
        }
        if(f[pos].child[i].isTerminal) {
            if (i == f.length - 1)
                return checkTerminals(f, ++pos,0) + 1;
            else return checkTerminals(f,pos,++i)+1;
        }
        return checkTerminals(f[pos].child,pos,i);
    }


    private boolean isEmpty(Fragment[]fragments){
        for (Fragment fragment : fragments) {
            if (fragment != null) return false;
        }
        return true;
    }

}
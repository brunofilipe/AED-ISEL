package series.serie2;

public class Utils {
	
	public static <E> Node<E>[] shrink(Node<E>[] hashTable){
        int d = hashTable.length;
        Node<E>[] newHash = new Node[d/2];
        int idx = 0;
        for (int i = 0; i < d; i++) {
                if (idx == newHash.length) {
                    idx = 0;
                }
                if (newHash[idx] == null) {
                    newHash[idx] = hashTable[i];
                } else {
                    if ( hashTable[i].next != hashTable[i]  ) {
                        Node<E> oldlast = newHash[idx].previous;
                        Node<E> newlast = hashTable[i].previous;
                        Node<E> oldfirst = hashTable[i].next;
                        oldfirst.previous = oldlast;
                        oldlast.next = oldfirst;
                        newHash[idx].previous = newlast;
                        newlast.next = newHash[idx];
                    }
                }
            ++idx;
        }
        return newHash;
	}


}

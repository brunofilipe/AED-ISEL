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
                }
                else {
                    if ( hashTable[i].next != hashTable[i]  ) {
                        insertInHash(hashTable[i], newHash[idx]);
                    }
                }
            ++idx;
        }
        return newHash;
	}

    private static <E> void insertInHash(Node<E> toInsert, Node<E> table) {
        Node<E> oldlast = table.previous;
        Node<E> newlast = toInsert.previous;
        Node<E> oldfirst = toInsert.next;
        oldfirst.previous = oldlast;
        oldlast.next = oldfirst;
        table.previous = newlast;
        newlast.next = table;
    }
}

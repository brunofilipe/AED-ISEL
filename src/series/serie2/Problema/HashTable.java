package series.serie2.Problema;

/**
 * Created by Bruno on 27/11/2016.
 */
public class HashTable<K,V> {
    private static class HNode<K,V> {
        public V data;
        public K key;
        public HNode<K, V> next;

        public HNode(K key, V value) { this.data = value; this.key = key; }
        public String toString() { return this.data.toString(); }
    }

    private int size;
    private HNode<K,V>[] data;
    private float loadFactor = 0.75f;
    private int count = 0;
    private final int DEFAULT_SIZE = 50;


    public HashTable(int size, float loadFactor) {
        this.size = size;
        this.loadFactor = loadFactor;
        data = new HNode[size];
    }


    public HashTable() {
        this.size = DEFAULT_SIZE;
        data = new HNode[size];
    }


    public HashTable(int size) {
        this.size = size;
        data = new HNode[size];
    }

    public V put(K key, V value) {
        if(key == null || value == null) return null;

        if(++count / (float)size >= loadFactor) rehash();
        int hash = this.hash(key);
        HNode<K, V> toInsert = new HNode<K,V>(key, value);
        if(data[hash] == null) {
            data[hash] = toInsert;
        } else {
            for(HNode<K,V> node = data[hash]; node != null; node = node.next) { //find duplicate and replace
                if(node.key.equals(key)) {
                    count--;
                    node.data = toInsert.data;
                    return node.data;
                }
            }
            toInsert.next = data[hash];
            data[hash] = toInsert;
        }

        return toInsert.data;
    }

    public V get(K key) {
        if(key == null) return null;
        int hash = this.hash(key);
        if(data[hash] == null) return null;
        for(HNode<K,V> node = data[hash]; node != null; node = node.next) {
            if(node.key.equals(key))
                return node.data;
        }
        return null;
    }

    public V remove(K key) {
        if(key == null) return null;

        int hash = this.hash(key);
        if(data[hash] == null) return null;

        HNode<K,V> toRemove;

        if(data[hash].next == null) {
            toRemove = data[hash];
            data[hash] = null;
            count--;
            return toRemove.data;
        } else {
            HNode<K,V> node, prev;
            for(node = data[hash].next, prev = data[hash]; node.next != null; prev = node, node = node.next) {
                if(node.key.equals(key)) {
                    count--;
                    prev.next = node.next;
                    return node.data;
                }
            }
        }
        return null;
    }

    private void rehash() {
        HNode<K,V>[] newArray = new HNode[size * 2];

        for(int i=0; i< size; ++i) {
            if(data[i] == null) continue;
            int hash = this.hash(data[i].key, size*2);
            newArray[hash] = data[i];
        }
        this.size *=2;
        this.data = newArray;
    }

    private int hash(K key) {
        return key.hashCode() % size;
    }

    private int hash(K key, int max) {
        return key.hashCode() % max;
    }

}

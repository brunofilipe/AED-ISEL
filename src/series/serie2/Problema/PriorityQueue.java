package series.serie2.Problema;
import java.util.Comparator;

import static series.serie1.Heap.*;


public class PriorityQueue<E,P>{

    public static class Pair<E, P> {

        private E elem;
        private P priority;

        public void setPriority(P priority) {
            this.priority = priority;
        }

        public E getElem() {
            return elem;
        }

        public P getPriority() {
            return priority;
        }

        public Pair(E elem,P priority){
            this.elem = elem;
            this.priority = priority;
        }
    }
    public static class Node {
        private int key;
        public Node next;
        private int index;
        public Node(int key,int index) {
            this.key = key;
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
        public int getKey() {
            return key;
        }
    }

    private final int INIT_HASH_DIMENSION = 10;
    private Pair<E,P>[] heap;
    private int sizeHeap=0;
    private int sizeNode=0;
    private Node [] table;
    private KeyExtractor<E> keyExtractor;
    private Comparator<P>cmp;


    public PriorityQueue(Comparator<P>cmp, KeyExtractor<E> keyE){
        heap = new Pair[INIT_HASH_DIMENSION];
        table = new Node[INIT_HASH_DIMENSION];
        this.cmp = cmp;
        this.keyExtractor = keyE;
    }

    public void add(E elem, P prio) {
        Pair<E, P>  pair = new Pair<>(elem,prio);
        increase(sizeHeap, pair);
        minHeapify(0,sizeHeap);
        ++sizeHeap;
    }

    public E pick() {
        return heap[0].getElem();
    }

    public E poll() {
        E v = pick();
        remove (keyExtractor.getKey(v));
        return v;
    }

    public void remove(int key){
        int i = getIndex(key);
        Node node = getNode( table[i],key); // removeNode return o old index
        if(node==null)return;
        int idx=node.getIndex();
        heap[idx]=heap[--sizeHeap];
        put(key,idx);
        heap[sizeHeap] = null;
        minHeapify(idx, sizeHeap);
        removeNode(node, i);
    }

    private void removeNode(Node node, int i) {
        Node prev = null;
        Node curr=table[i];

        while ( curr != null  ) {
            if ( node.getIndex() == curr.index && node.getKey()== curr.key ){
                --sizeNode;
            }
            if(prev == null){
                table[i] =curr.next;
            }
            else {
                prev.next = curr.next;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public void update(int key, P prio) {
        int i = getIndex(key);
        int idx = table[i].getIndex();
        heap[idx].setPriority(prio);

        Node n = getNode(table[i],key);


        minHeapify(idx, sizeHeap);
    }

    private Node getNode(Node curr,int key) {
        while ( curr != null ) {
            if ( key== curr.key )
                return curr;
            curr = curr.next;
        }
        return null;
    }

    private void expandTable() {
        int newDim = table.length << 1;
        Node[] oltT = table;
        table = new Node[newDim];
        for ( int i = 0; i < oltT.length; ++ i ) {
            Node curr;
            while ( oltT[i] != null ) {
                curr = oltT[i];
                oltT[i] = oltT[i].next;
                int index = getIndex( curr.key );
                curr.next = table[index];
                table[index] = curr;
            }
        }

    }

    private int getIndex(int key) {
        return key%table.length;
    }

    private void increase( int i, Pair<E,P>  v ) {
        int p;
        while ( i > 0 && cmp.compare( heap[p = parent( i )].getPriority(), v.getPriority()) < 0 ) {
            heap[i] = heap[p];
            put(keyExtractor.getKey(heap[p].getElem()), i);
            i = p;
        }
        heap[i] = v;
        put(keyExtractor.getKey(v.getElem()), i);
    }

    private void put(int key, int index) {
        Node no;
        int i = getIndex(key);
        no = getNode( table[i],key);
        if ( no != null ) no.index= index;
        else {
            if ( sizeNode == table.length ) expandTable();
            i = getIndex(key);
            no = new Node(key,index);
            no.next = table[i];
            table[i] = no;
            ++sizeNode;
        }
    }
    public void minHeapify(int pos, int size) {
        int largest = pos;
        int l = left(pos), r = right(pos);
        while (heap[r] == null){
            --r;
        }
        if ((l <= size) && (cmp.compare(heap[largest].getPriority(), heap[r].getPriority()) > 0))
            largest = l;
        if ((r <= size) && (cmp.compare(heap[largest].getPriority(), heap[r].getPriority()) > 0))
            largest = r;
        if (largest != pos) {
            Pair aux = heap[pos];
            heap[pos] = heap[largest];
            put(keyExtractor.getKey(heap[pos].getElem()),pos);
            heap[largest] = aux;
            put(keyExtractor.getKey(heap[largest].getElem()),largest);
            minHeapify( largest, size);
        }
    }
}
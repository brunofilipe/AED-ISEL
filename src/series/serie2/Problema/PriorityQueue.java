package series.serie2.Problema;
import java.util.Comparator;
import java.util.NoSuchElementException;

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
        public int getIndex() {
            return index;
        }

        public int getKey() {
            return key;
        }

        private int index;
        public Node(int key,int index) {
            this.key = key;
            this.index = index;
        }
    }

    private final int INIT_HASH_DIMENSION = 10;
    private Pair<E,P>[] heap;
    private int size = 0;
    private int sizeNode = 0;
    private Node [] table;
    private int dimension;
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
        increase( size, pair);
        ++size;
    }

    private void put(int key, int index) {
        Node no;
        int i = getIndex(key);
        no = getNode( table[i],key,i );
        if ( no != null ) no.index= index;
        else {
            if ( sizeNode == table.length ) expand();
            i = getIndex(key);
            no = new Node(key,index);
            no.next = table[i];
            table[i] = no;
            ++sizeNode;
        }
    }
    private Node getNode(Node curr,int key,int idx) {
        while ( curr != null ) {
            if ( idx == curr.index && key== curr.key )
                return curr;
            curr = curr.next;
        }
        return null;
    }

    private void expand() {
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

    public   void maxHeapify( int pos, int size) {
        int largest = pos;
        int l = left(pos), r = right(pos);
        if ((l < size) && (cmp.compare(heap[largest].getPriority(), heap[r].getPriority()) > 0))
            largest = l;
        if ((r < size) && (cmp.compare(heap[largest].getPriority(), heap[r].getPriority()) > 0))
            largest = r;
        if (largest != pos) {
            Pair aux = heap[pos];
            heap[pos] = heap[largest];
            //put
            heap[largest] = aux;
            //put
            maxHeapify( largest, size);
        }
    }

    public E pick() {
        return heap[0].getElem();
    }

    public E poll() {
        E v = pick();
        remove ( keyExtractor.getKey( v ) );
        return v;
    }

    public void update(int key, P prio) {
        int i = getIndex(key);
        int idx = table[i].getIndex(); // getNode
        heap[idx].setPriority(prio);
        maxHeapify(idx,size);
    }
    public void remove(int key){
        int i = getIndex(key);
        Node n = getNode( table[i]); // removeNode return o old index
        int idx= n.getIndex();
        Pair p = heap[idx];
        heap[idx]=heap[--size];
        //put
        heap[size] = null;
        maxHeapify(idx,size);
        //removenode table[i]=null;
    }
}

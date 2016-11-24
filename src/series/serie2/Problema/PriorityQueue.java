package series.serie2.Problema;
import java.util.Comparator;
import static series.serie1.Heap.left;
import static series.serie1.Heap.parent;
import static series.serie1.Heap.right;


public class PriorityQueue<E,P>{

    public static class Pair<E, P> {
        public E getElem() {
            return elem;
        }
        E elem;

        public P getPriority() {
            return priority;
        }

        P priority;
        public Pair(E elem,P priority){
            this.elem = elem;
            this.priority = priority;
        }
    }

    public static class Node {
        int key;
        int index;
        public Node(int key,int index) {
            this.key = key;
            this.index = index;
        }
    }

    private Pair<E,P>[] heap;    // maxHeapify, increase
    private int size = 0;

    private Node [] table;       // getIndex(key), put( chave, index ),  remove(key)
    private int dimension;
    private KeyExtractor<E> keyExtractor;
    private Comparator<P>cmp;



    public PriorityQueue(int capacity,Comparator<P>cmp, KeyExtractor<E> keyE){
        heap = (Pair<E,P>[])new Object[capacity];
        table = (Node[]) new Object[capacity];
        this.cmp = cmp;
        dimension=capacity;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E elem, P prio) {
        Pair<E, P>  pair = new Pair<>(elem,prio);
        //heap[size] = pair;
        //put(keyExtractor.getKey(pair.getElem()),size);
        increase( size, pair);
        ++size;
    }

    private void put(int key, int index) {
        Node no = new Node(key,index);
        int i = getIndex(key);
        if(table [i] == null){
            table[i] = no;
        }
    }

    private int getIndex(int key) {
        return key%dimension;
    }

    private void increase( int i, Pair<E,P>  v ) {
        int p;
        while ( i > 0 && cmp.compare( heap[p = parent( i )].getPriority(), v.getPriority()) < 0 ) {
            heap[i] = heap[p];
            put(keyExtractor.getKey(heap[p].getElem()), i);
            i = p;
        }
        heap[i] = v;
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
            heap[largest] = aux;
            maxHeapify( largest, size);
        }
    }


    public E pick() {
        return heap[0].getElem();
    }

    public E poll() {
        E v = pick();
        heap[0] = heap[--size];
        heap[size] = null;
        maxHeapify(0,size);
        return v;
    }

    public void update(int key, P prio) {
        int i = getIndex(key);
        int idx = table[i].index;
        heap[idx].priority=prio;
        maxHeapify(idx,size);
    }
    public void remove(int key){
        int i = getIndex(key);
        int idx= table[i].index;
        Pair p = heap[idx];
        heap[idx]=heap[--size];
        heap[size] = p;
        maxHeapify(idx,size);
        table[i]=null;
    }
}

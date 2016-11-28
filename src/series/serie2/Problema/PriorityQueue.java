package series.serie2.Problema;
import java.util.Comparator;



public class PriorityQueue<E,P>{

    private static class Pair<E, P> {
        public Pair(E e, P p, int key) {
            this.data = e;
            this.priority = p;
            this.key = key;
        }
        int key;
        E data;
        P priority;
    }

    private HashTable<Integer, Integer> table;
    private Pair<E, P>[] heap;
    private int count;
    private Comparator<P>cmp;
    private KeyExtractor<E> keyExtractor;

    public PriorityQueue(int size,Comparator<P>cmp,KeyExtractor<E>keyExtractor) {
        this.table = new HashTable<> (size, 2);             //loadFactor = 2 para nao ultrapassar o size
        this.heap = (Pair<E, P>[])new Pair[size];
        this.count = 0;
        this.cmp = cmp;
        this.keyExtractor = keyExtractor;
    }

    public HashTable<Integer,Integer> getTable() {
        return table;
    }

    public P getPriority(int idx){
        return heap[idx].priority;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return this.count == this.heap.length;
    }

    public void add(E e, P p) {
        if(e == null || p == null || keyExtractor == null || isFull()) return;
        int hash = keyExtractor.getKey(e);
        int index = increase(this.count, p);
        heap[index] = new Pair<>(e, p, hash);
        this.table.put(hash, index);
        this.count++;
    }

    public E pick() {
        if(isEmpty()) return null;
        return heap[0].data;
    }

    public E poll() {
        E max = pick();
        this.table.remove(heap[0].key);

        int index = decrease(0);

        this.count--;
        switchHeap(index, this.count);
        heap[this.count] = null;
        return max;
    }

    public void update(int key, P prio) {
        int heapIdx = this.table.get(key);
        this.table.remove(key);

        int result = cmp.compare(heap[heapIdx].priority,prio);
        heap[heapIdx].priority = prio;

        Pair elem = heap[heapIdx];

        int i;
        if(result < 0) //prio is bigger
            i = increase(heapIdx, prio);
        else
            i = decrease(heapIdx);

        heap[i] = elem;
        this.table.put(elem.key, i);
    }

    public void remove(int key) {
        int heapIdx = this.table.get(key);
        this.table.remove(key);
        this.count--;

        switchHeap(heapIdx, this.count);
        heap[this.count] = null;

        decrease(heapIdx);
    }

    private int increase(int start, P p) {
        int i;
        for(i = start; i > 0 && cmp.compare(heap[Utils.parent(i)].priority,p) < 0; i = Utils.parent(i)) //bubble up
            switchHeap(i, Utils.parent(i));

        return i;
    }

    private int decrease(int start) {
        int i, son;

        for(i = start;Utils.left(i) <= this.count-1; i = son) {
            son = Utils.left(i);
            if(son < this.count-1 && cmp.compare(heap[son].priority,heap[son+1].priority) < 0) son++;

            if(cmp.compare(heap[son].priority,heap[this.count-1].priority) > 0) swap(i,son);
                //switchHeap(i, son);
            else
                break;
        }

        return i;
    }

    private void swap(int i, int son) {
         Pair aux = heap[i];
         heap[i] = heap[son];
         heap[son] = aux;
    }

    private void switchHeap(int i, int i2) {
        heap[i] = heap[i2];
        table.put(heap[i2].key, i);
    }




}

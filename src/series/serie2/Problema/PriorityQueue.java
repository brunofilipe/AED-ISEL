package series.serie2.Problema;
import java.util.Comparator;



public class PriorityQueue<E,P>{

    private static class Node<E, P> {
        public Node(E e, P p, int key) {
            this.data = e;
            this.priority = p;
            this.key = key;
        }
        int key;
        E data;
        P priority;
    }

    private HashTable<Integer, Integer> table;
    private Node<E, P>[] heap;
    private int count;
    private Comparator<P>cmp;
    private KeyExtractor<E> keyExtractor;

    public PriorityQueue(int size,Comparator<P>cmp,KeyExtractor<E>keyExtractor) {
        this.table = new HashTable<Integer, Integer> (size);
        this.heap = (Node<E, P>[])new Node[size];
        this.count = 0;
        this.cmp = cmp;
        this.keyExtractor = keyExtractor;
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
        int index = bubbleUp(this.count, p);
        heap[index] = new Node<E,P>(e, p, hash);
        this.table.put(hash, index);
        this.count++;
    }

    public E pick() {
        if(isEmpty()) return null;
        return heap[0].data;
    }

    public E poll() {
        if(isEmpty()) return null;

        E max = heap[0].data;
        this.table.remove(heap[0].key);

        int index = bubbleDown(0);

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

        Node elem = heap[heapIdx];

        int i;
        if(result < 0) //prio is bigger
            i = bubbleUp(heapIdx, prio);
        else
            i = bubbleDown(heapIdx);

        heap[i] = elem;
        this.table.put(elem.key, i);
    }

    public void remove(int key) {
        int heapIdx = this.table.get(key);
        this.table.remove(key);
        this.count--;

        switchHeap(heapIdx, this.count);
        heap[this.count] = null;

        bubbleDown(heapIdx);
    }

    public P pickKey() {
        if(isEmpty()) return null;

        return heap[0].priority;
    }

    private int bubbleUp(int start, P p) {
        int i;
        for(i = start; i > 0 && cmp.compare(heap[(i-1)/2].priority,p) < 0; i = (i-1)/2) //bubble up
            switchHeap(i, (i-1)/2);

        return i;
    }

    private int bubbleDown(int start) {
        int i, son;

        for(i = start; i*2+1 <= this.count-1; i = son) {
            son = i*2+1;
            if(son < this.count-1 && cmp.compare(heap[son].priority,heap[son+1].priority) < 0) son++;

            if(cmp.compare(heap[son].priority,heap[this.count-1].priority) > 0)
                switchHeap(i, son);
            else
                break;
        }

        return i;
    }

    private void switchHeap(int i, int i2) {
        heap[i] = heap[i2];
        this.table.put(heap[i2].key, i);
    }

    public static int parent(int i) { return (i-1) >> 1; }
    public static int left(int i) { return (i << 1) + 1; }
    public static int right(int i) { return (i << 1) + 2; }


}

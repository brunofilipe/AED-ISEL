package series.serie3;


import series.serie2.Problema.Utils;

import java.util.Comparator;


public class PriorityQueue<E,P>{

    private static class Pair<E, P> {
        int key;
        E data;
        P priority;
        public Pair(E e, P p) {
            this.data = e;
            this.priority = p;
            this.key = this.data.hashCode();
        }
    }

    private HashTable<Integer, Integer> table;
    private Pair<E, P>[] heap;
    private int count;
    private Comparator<P>cmp;


    public PriorityQueue(int size, Comparator<P>cmp) {
        this.table = new HashTable<>(size, 2);             //loadFactor = 2 para nao ultrapassar o size
        this.heap = (Pair<E, P>[])new Pair[size];
        this.count = 0;
        this.cmp = cmp;

    }
    public PriorityQueue(Comparator<P>cmp){
        this(30,cmp);
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
        Pair<E, P> toAdd = new Pair<>(e, p);
        int index = increase(count,p);
        table.put(toAdd.key, index);
        heap[count] = toAdd;
        count++;
    }

    public E pick() {
        if(isEmpty()) return null;
        return heap[0].data;
    }

    public E search(E value ){
        E res = null;
        for (int i = 0; i < count; ++i){
            if(heap[i].data.equals(value)) res = value;
        }
        return res;
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

    public void sortHeap(){
        for (int j = 0 ; j < count; ++j) {
            Pair<E,P> key = heap[j];
            int i = j-1;
            for ( ; i >= 0 && cmp.compare(heap[i].priority, key.priority) < 0; -- i)
                heap[i+1] = heap[i];
            heap[i+1] = key;
        }
    }
}
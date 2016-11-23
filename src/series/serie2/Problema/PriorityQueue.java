package series.serie2.Problema;

import series.serie1.Heap;


import java.util.Comparator;
import java.util.NoSuchElementException;


public class PriorityQueue<E,P>implements KeyExtractor<E> {

    private E[]heap;
    private int size = 0;
    Comparator<P>cmp;


    public PriorityQueue(int capacity,Comparator<P>cmp){
        heap = (E[])new Object[capacity];
        this.cmp = cmp;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E elem, P prio) {
        heap[size] = elem;
       // Heap.increase(heap,size,P,elem);
        ++size;
    }

    public E pick() {
        return heap[0];
    }

    public E poll() {
        E v = pick();
        heap[0] = heap[--size];
        heap[size] = null;
        //Heap.maxHeapify(heap,0,size,cmp);
        return v;
    }

    public void update(int key, P prio) {

    }

    @Override
    public int getKey(E e) {
        for (int i = 0; i < heap.length; i++) {
            if(heap[i]==e) return i;
        }
       throw new NoSuchElementException("Não encontrou nenhum e");
    }
}

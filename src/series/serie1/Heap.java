package series.serie1;

import java.util.Comparator;


public class Heap {
    public static int parent(int i) { return (i-1) >> 1; }
    public static int left(int i) { return (i << 1) + 1; }
    public static int right(int i) { return (i << 1) + 2; }

    public static void maxHeapify(int[] array, int pos, int size, Comparator<Integer> cmp) {
        int largest = pos;
        int l = left(pos), r = right(pos);
        if ((l < size) && (cmp.compare(array[largest], array[l]) < 0))
            largest = l;
        if ((r < size) && (cmp.compare(array[largest], array[r]) < 0))
            largest = r;
        if (largest != pos) {
            int aux = array[pos];
            array[pos] = array[largest];
            array[largest] = aux;
            maxHeapify(array, largest, size, cmp);
        }
    }
    public static void minHeapify(int[] array, int pos, int size, Comparator<Integer> cmp) {
        int largest = pos;
        int l = left(pos), r = right(pos);
        if ((l < size) && (cmp.compare(array[largest], array[l]) > 0))
            largest = l;
        if ((r < size) && (cmp.compare(array[largest], array[r]) > 0))
            largest = r;
        if (largest != pos) {
            int aux = array[pos];
            array[pos] = array[largest];
            array[largest] = aux;
            minHeapify(array, largest, size, cmp);
        }
    }

    public static void increase(int[] heap, int i, int v ) {
        int p;
        while ( i > 0 && heap[p = parent( i )] < v  ) {
            heap[i] = heap[p];
            i = p;
        }
        heap[i] = v;
    }

    public static <E> void increase(E[] heap, int i, Comparator< E> c, E v ) {
        int p;
        while ( i > 0 && c.compare( heap[p = parent( i )], v) < 0 ) {
            heap[i] = heap[p];
            i = p;
        }
        heap[i] = v;
    }


    public static <E> void minHeapify(E[] array, int pos, Comparator<E>cmp, int size) {
        int largest = pos;
        int l = left(pos), r = right(pos);
        if ((l < size) && (cmp.compare(array[largest], array[l]) > 0))
            largest = l;
        if ((r < size) && (cmp.compare(array[largest], array[r]) > 0))
            largest = r;
        if (largest != pos) {
            E aux = array[pos];
            array[pos] = array[largest];
            array[largest] = aux;
            minHeapify(array, largest, cmp, size);
        }

    }
    public static <E> void maxHeapify(E[] array, int pos, int size, Comparator<E> cmp) {
        int largest = pos;
        int l = left(pos), r = right(pos);
        if ((l < size) && (cmp.compare(array[largest], array[l]) > 0))
            largest = l;
        if ((r < size) && (cmp.compare(array[largest], array[r]) > 0))
            largest = r;
        if (largest != pos) {
            E aux = array[pos];
            array[pos] = array[largest];
            array[largest] = aux;
            maxHeapify(array, largest, size, cmp);
        }
    }

    public static <E> void buildMinHeap(E[] array, int size, Comparator<E> cmp) {
        int iPai = parent(size - 1);
        while (iPai >= 0) {
            minHeapify(array, iPai, cmp,size);
            iPai--;
        }
    }
    public static  void buildMinHeap(int []array, int size, Comparator<Integer> cmp) {
        int iPai = parent(size - 1);
        while (iPai >= 0) {
            minHeapify(array, iPai, size, cmp);
            iPai--;
        }
    }
    public static  void buildMaxHeap(int []array, int size, Comparator<Integer> cmp) {
        int iPai = parent(size - 1);
        while (iPai >= 0) {
            maxHeapify(array, iPai, size, cmp);
            iPai--;
        }
    }
    public static void maxheapSort( int[] a,Comparator<Integer> cmpr ) {
        buildMaxHeap(a, a.length,cmpr);
        for ( int i = a.length-1; i > 0 ; --i){
            swap( a, 0, i);
            maxHeapify(a, 0, i,cmpr);
        }
    }
    public static void minheapSort( int[] a,Comparator<Integer> cmpr ) {
        buildMinHeap(a, a.length,cmpr);
        for ( int i = a.length-1; i > 0 ; --i){
            swap( a, 0, i);
            minHeapify(a, 0, i,cmpr);
        }
    }
    public static void swap(int[] a, int i1, int i2) {
        int aux = a[i1];
        a[i1] = a[i2];
        a[i2] = aux;
    }

    public static void main(String[] args) {
        int[]a = {5,2,9,6};
        Comparator<Integer> cmpr = (c1,c2)->Math.abs(c1-c2);
        minheapSort(a,cmpr);
        for (int i = 0; i < a.length; i++) {System.out.println(a[i]);}
    }
}

package series.serie1;

import java.util.*;

/**
 * Created by Bruno on 09/04/2016.
 */
public class Heap {
    private static int parent(int i) { return (i-1) >> 1; }
    private static int left(int i) { return (i << 1) + 1; }
    private static int right(int i) { return (i << 1) + 2; }

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
            Integer aux = array[pos];
            array[pos] = array[largest];
            array[largest] = aux;
            minHeapify(array, largest, size, cmp);
        }
    }

    public static void heapSort(int[] array, int size, Comparator<Integer> cmp) {
        buildMaxHeap(array, size, cmp);
        while (size >= 1){
            int aux = array[size - 1];
            array[size - 1] = array[0];
            array[0] = aux;
            maxHeapify(array, 0, --size, cmp);
        }
    }

    public static void buildMinHeap(int[] array, int size, Comparator<Integer> cmp) {
        int iPai = parent(size - 1);
        while (iPai >= 0) {
            minHeapify(array, iPai, size, cmp);
            iPai--;
        }
    }

    public static void buildMaxHeap(int[] array, int size, Comparator<Integer> cmp) {
        int iPai = parent(size - 1);
        while (iPai >= 0) {
            maxHeapify(array, iPai, size, cmp);
            iPai--;
        }
    }

    /*
        generic heap
     */

    public static <E> void maxHeapify(E[] array, int pos, int size, Comparator<E> cmp) {
        int largest = pos;
        int l = left(pos), r = right(pos);
        if ((l < size) && (cmp.compare(array[largest], array[l]) < 0))
            largest = l;
        if ((r < size) && (cmp.compare(array[largest], array[r]) < 0))
            largest = r;
        if (largest != pos) {
            E aux = array[pos];
            array[pos] = array[largest];
            array[largest] = aux;
            maxHeapify(array, largest, size, cmp);
        }
    }

    public static <E> void minHeapify(E[] array, int pos, int size, Comparator<E> cmp) {
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
            minHeapify(array, largest, size, cmp);
        }
    }

    public static <E> void heapSort(E[] array, int size, Comparator<E> cmp) {
        buildMaxHeap(array, size, cmp);
        while (size >= 1){
            E aux = array[size - 1];
            array[size - 1] = array[0];
            array[0] = aux;
            maxHeapify(array, 0, --size, cmp);
        }
    }

    public static <E> void buildMinHeap(E[] array, int size, Comparator<E> cmp) {
        int iPai = parent(size - 1);
        while (iPai >= 0) {
            minHeapify(array, iPai, size, cmp);
            iPai--;
        }
    }

    private static <E> void buildMaxHeap(E[] array, int size, Comparator<E> cmp) {
        int iPai = parent(size - 1);
        while (iPai >= 0) {
            maxHeapify(array, iPai, size, cmp);
            iPai--;
        }
    }
}

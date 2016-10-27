package series.serie1;
import java.util.Comparator;


public class Heap {
    public static int parent(int i) { return (i-1) >> 1; }
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

    public static void increase(int[] heap, int i, int v ) {
        int p;
        while ( i > 0 && heap[p = parent( i )] < v  ) {
            heap[i] = heap[p];
            i = p;
        }
        heap[i] = v;
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

    public static <E> void buildMinHeap(E[] array, int size, Comparator<E> cmp) {
        int iPai = parent(size - 1);
        while (iPai >= 0) {
            minHeapify(array, iPai, size, cmp);
            iPai--;
        }
    }
}
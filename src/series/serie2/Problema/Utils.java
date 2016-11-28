package series.serie2.Problema;


public class Utils {
    public static int parent(int i) { return (i-1) >> 1; }
    public static int left(int i) { return (i << 1) + 1; }
    public static int right(int i) { return (i << 1) + 2; }


    public static void minHeapify(int[] heap, int heapSize, int i) {
        int l = left(i);
        int r = right( i );
        int largest = ( l >= heapSize || heap[ l ] > heap[i] ) ? i : l;
        if ( r < heapSize && heap[ largest ] > heap[ r ] ) largest = r;
        if ( largest != i ) {
            swap( heap, i, largest );
            minHeapify( heap, heapSize, largest);
        }
    }

    private static void swap(int[] a, int i1, int i2) {
        int aux = a[i1];
        a[i1] = a[i2];
        a[i2] = aux;
    }

}

package series.serie1;

import java.util.Comparator;

/**
 * Created by msousa on 10/4/2016.
 * Metodos a implementar na serie 1
 */
public class Arrays{

   public static int greatestAfterRotate(int[] v, int left, int right) {
        if (left > right) return -1;//0
        if (right - left <= 1) return (v[left] > v[right]) ? v[left] : v[right];//O(1)
        else {
            int mid = left + (right - left) / 2;//0
            return v[left] > v[mid] ? greatestAfterRotate(v, left, mid) ://C(n/2)
                                      greatestAfterRotate(v, mid, right);

        }

    }

    public static boolean isPermutation(int[] a1, int l1, int r1, int[] a2, int l2, int r2){
        int dim = r1 - l1 + 1;
        if(dim != r2 - l2 + 1) return false;
        mergeSort(a1,l1,r1);
        mergeSort(a2,l2,r2);
        boolean res =true;
        for (int i = 0; i < dim; i++) {
            if(a1[i + l1] != a2[i + l2]) res = false;
        }
        return res;
    }


    public static void changeValueInMaxHeap(int[] v, int count, int ix, int newValue){
        Comparator<Integer> comp = (cmp1, cmp2) -> cmp1 - cmp2;
        if( ix >=  count ) {throw new IllegalArgumentException(); }
        if ( v[ix] < newValue )
            Heap.increase(v,ix,newValue);
        else{
            v[ix] = newValue;
            Heap.maxHeapify(v, ix,count,comp);
        }
    }

    public static void sortIPv4Addresses(String[] v, int l, int r) {
        if(v.length < 2) return;
        int length = v[0].split("\\.").length;

        String[]a = v,save;
        String[]  b = new String[r + 1];
        for (int i = length -1; i>= l; i--) {
            countingSort(a,b, l, r, 256, i);
            save = a;a = b;b = save;
        }
        System.arraycopy(a,0,v,l,b.length);
    }
    private static void countingSort(String[] a,String [] b, int l, int r, int length, int idx) {
        int[] c = new int[length];
        int i2;
        for (int i = l; i <= r; ++i) {++c[getIp(a[i],idx)];}

        for (int i = 1; i < length; ++i) {c[i] += c[i-1];}

        for (int i = r; i >= l; --i) {
            String s = a[i];
            i2 = c[getIp(a[i],idx)] - 1;
            --c[getIp(a[i],idx)];
            b[i2] = s;
        }
    }

    private static int getIp(String s,int idx){
        return Integer.parseInt(s.split("\\.")[idx]);
    }

    public static void mergeSort(int[] a, int l, int r) {
        if (l < r) {
            int m = (l+r) >>> 1;
            mergeSort(a, l, m);
            mergeSort(a, m+1, r);
            merge(a, l, m, r);
        }
    }

    private static void merge(int[] a, int l, int m, int r) {
        int[] arrayLeft = new int[ m - l + 1 ];
        System.arraycopy(a, l, arrayLeft, 0, arrayLeft.length);
        int iA = l, iL= 0, iR= m+1;
        while( iL < arrayLeft.length && iR <= r ) {
            if ( arrayLeft[iL] <= a[iR])
                a[iA++]= arrayLeft[iL++];
            else
                a[iA++]= a[iR++];
        }
        System.arraycopy(arrayLeft, iL, a, iA, arrayLeft.length - iL);
    }

}

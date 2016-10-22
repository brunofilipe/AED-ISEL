package series.serie1;

import java.util.Comparator;

/**
 * Created by msousa on 10/4/2016.
 * Metodos a implementar na serie 1
 */
public class Arrays{
    public static void main(String[] args) {

        String[]a1 = {"192.168.1.1",
                      "192.168.1.4",
                      "80.68.11.31",
                      "192.168.10.33",
                      "192.168.11.3",
                      "81.67.12.31"};

        sortIPv4Addresses(a1,0,5);
        for (int i = 0; i < a1.length; ++i) {
            System.out.println(a1[i]);
        }
        //int great = greatestAfterRotate(a,0,5);
        //System.out.println(great);
    }
    public static int greatestAfterRotate(int[] v, int left, int right) {
        if (left > right) return -1;
        if (right - left <= 1) return (v[left] > v[right])?v[left]:v[right];
         else {
            int mid = left + (right - left) / 2;
            /*return (v[left]>v[right]) ?
                    greatestAfterRotate(v, left, mid) :
                    greatestAfterRotate(v, mid + 1, right);*/
            int leftMax = greatestAfterRotate(v, left, mid);
            int rightMax = greatestAfterRotate(v, mid + 1, right);
            return (leftMax > rightMax) ? leftMax : rightMax;
        }
    }

    public static boolean isPermutation(int[] a1, int l1, int r1, int[] a2, int l2, int r2) {
        int dim = r1 - l1 + 1;
        int count=0;
        if(dim!=r2-l2+1) return false;
        boolean[]aux=new boolean[dim];
        for (int i = l1; i <= r1 ; i++)
            for (int j=l2;j <= r2 ; j++)
                if(a1[i]==a2[j] && !aux[j-l2]) {
                    aux[j-l2] = true;
                    break;
                }
        for (int k = 0; k <aux.length ; k++)
            if(aux[k])
                ++count;
        return count == dim;
    }

    public static void changeValueInMaxHeap(int[] v, int count, int ix, int newValue){
        Comparator<Integer> comp = (cmp1, cmp2) -> Math.abs(cmp1) - Math.abs(cmp2);
        boolean exists = false;
        int i = 0;
        while (i < count && !exists) {
            if(i == ix){
                v[i] = newValue;
                exists = true;
            }
            i++;
        }
        if(!exists) {throw new IllegalArgumentException(); }
        while (ix>=0) Heap.maxHeapify(v,ix--,count,comp);
    }

    public static void sortIPv4Addresses(String[] v, int l, int r) {
        if(v.length < 2) return;
        int length = v[0].split("\\.").length;
        String[]a = v,save;
        String[]  b = new String[r + 1];
        for (int i = length -1; i>= l; i--) {
            countingSort(a,b, l, r, 256, i);
            save = a;
            a = b;
            b = save;
        }
        System.arraycopy(a,0,v,l,b.length);
    }
    private static void countingSort(String[] a,String [] b, int l, int r, int length, int idx) {
        int[] c = new int[length];
        int i2;
        for (int i = l; i <= r; ++i) {
            ++c[Integer.parseInt(a[i].split("\\.")[idx])];
        }
        for (int i = 1; i < length; ++i) {c[i] += c[i-1];}
        for (int i = r; i >= l; --i) {
            String s = a[i];
            i2 = c[Integer.parseInt(a[i].split("\\.")[idx])] - 1;
            --c[Integer.parseInt(s.split("\\.")[idx])];
            b[i2] = s;
        }
    }
}

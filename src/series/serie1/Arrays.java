package series.serie1;

import java.util.Comparator;

/**
 * Created by msousa on 10/4/2016.
 * Metodos a implementar na serie 1
 */
public class Arrays{
    public static void main(String[] args) {

        String[]a1 = {"192.168.1.1","192.168.1.4","80.68.11.31","81.67.12.31"};

        sortIPv4Addresses(a1,0,3);
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
    /*
    public static void changeValueInMaxHeap(int[] v, int count, int ix, int newValue) {
        Comparator<Integer> comp = (cmp1, cmp2) -> Math.abs(cmp1) - Math.abs(cmp2);
        if(ix>=count)throw new IllegalArgumentException();
        v[ix] = newValue;
        Heap.buildMaxHeap(v,count,comp);
    }
*/
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
        int [] a = v, b = new int [v.length], aux;

        String[] split = v[l].split("\\.");
        for (int i = 0; i < split.length; i++) {


            int auxi = l + 1 ;
            for (int j = l; j <= r && auxi <=r ; ) {
                if(!v[j].split("\\.")[i].equals(v[auxi].split("\\.")[i])){
                    ++j;
                    ++auxi;
                }
                else{
                    while (auxi + 1 <=r && v[auxi +1 ].split("\\.")[i].equals(v[j].split("\\.")[auxi]) )
                        ++auxi;
                    aux(v,i,j,auxi);
                    j = auxi;
                    auxi++;
                }

            }

        }
    }
/*
    public static void countingSort([] a, int[] b, int n, int k) {
        int[] c = new int[k+1];
        for (int j = 0; j < n; ++j)
            ++c[ a[j] ];
        for (int i = 1; i < c.length; ++i)
            c[i]+= c[i-1];
        for (int j= n-1; j >=0; --j) {
            b[ c[ a[j] ] - 1 ] = a[j];
            -- c[ a[j] ];
        }
    }*/

    private static void aux(String[] v, int idx, int l, int r) {
        for (int j = l + 1; j <= r; ++j) {
            String key = v[j];
            int i = j - 1;
            for (; i >= l && Integer.parseInt(v[i].split("[.]")[idx]) > Integer.parseInt(key.split("[.]")[idx]); --i) {
                v[i + 1] = v[i];
            }
            v[i + 1] = key;
        }
    }
}

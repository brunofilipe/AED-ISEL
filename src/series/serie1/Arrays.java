package series.serie1;

/**
 * Created by msousa on 10/4/2016.
 * M�todos a implementar na s�rie 1
 */
public class Arrays{
    public static void main(String[] args) {
        int[]a = {27,29,45,42,5,15};
        int great = greatestAfterRotate(a,0,5);
        System.out.println(great);
    }




    public static int greatestAfterRotate(int[] v, int left, int right) {
        if ( left > right ) return -1;
        int m = ( left + right ) >>> 1;
        int value = v[left];
        int firstHalf = search(v,left,m,value);
        int secondHalf = search(v,m+1,right,value);
        if(v[firstHalf]>v[secondHalf])return v[firstHalf];
        else return v[secondHalf];
    }

    public static int search(int[] sortedArray, int l, int r,int value) {
        if(l>r) return -1;
        if(sortedArray[l] > value) return l;
        if ( value > sortedArray[l] )  ++l;
        return search(sortedArray, l, r, value );

    }







    public static boolean isPermutation(int[] a1, int l1, int r1, int[] a2, int l2, int r2) {

        return false;
    }

    public static void changeValueInMaxHeap(int[] v, int count, int ix, int newValue){
    }

    public static void sortIPv4Addresses(String[] v, int l, int r) {
    }

}

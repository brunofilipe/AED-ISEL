package series.serie1;

/**
 * Created by msousa on 10/4/2016.
 * M�todos a implementar na s�rie 1
 */
public class Arrays{
    public static void main(String[] args) {
        int[]a = {27,29,35,42,5,15};
        int great = greatestAfterRotate(a,0,5);
        System.out.println(great);
        /*int[]aux1 = {1,3,4,4};
        int[]aux2 = {1,4,3,3};
        System.out.println(isPermutation(aux1,0,3,aux2,0,3));*/
    }
    public static int greatestAfterRotate(int[] v, int left, int right) {
        if (left > right) return -1;
        if (right - left <= 1) return (v[left] > v[right])?v[left]:v[right];
         else {
            int mid = left + (right - left) / 2;
            int leftMax = greatestAfterRotate(v, left, mid);
            int rightMax = greatestAfterRotate(v, mid + 1, right);
            return (leftMax > rightMax) ? leftMax : rightMax;
        }
    }

   /* public static int max(int[] sortedArray, int l, int r) {
        if (l == r) {
            return sortedArray[l];
        } else if (sortedArray[l] < sortedArray[r]) {
            return max(sortedArray, l + 1, r);
        } else {
            return sortedArray[l];
        }
    }*/

    public static boolean isPermutation(int[] a1, int l1, int r1, int[] a2, int l2, int r2) {
        int dim = r1-l1 + 1;
        if(dim != r2-l2 + 1) return false;
        int count = 0;
        int startR2 = r2;
        while(l1<=r1 && r2>=l2 ){
            if(a1[l1] == a2[r2] ){
                ++count;
                ++l1;
                r2 = startR2;
            }
            else --r2;
        }
        return count == dim;
    }
    /*public static boolean isPermutation(int[] a1, int l1, int r1, int[] a2, int l2, int r2) {
        int dim = r1-l1 + 1;
        int count = 0;

        for (int i = l1; i <=r1 ; i++) {
            for (int j = l2; j <=r2; j++) {
                if (a1[i] == a2[j]) {
                        ++count;
                }

            }
        }

        return count == dim;
    }*/
    public static void changeValueInMaxHeap(int[] v, int count, int ix, int newValue){
    }

    public static void sortIPv4Addresses(String[] v, int l, int r) {
    }

}

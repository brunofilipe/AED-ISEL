package series.serie1;

/**
 * Created by msousa on 10/4/2016.
 * M�todos a implementar na s�rie 1
 */
public class Arrays{
    public static void main(String[] args) {
        //int[]a = {27,29,45,42,5,15};
        int []a1 = {1,3,4,2};
        int []a2 = {4,2,3,0};
        System.out.println(isPermutation(a1,0,3,a2,0,3));
        //int great = greatestAfterRotate(a,0,5);
        //System.out.println(great);
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
    }

    public static void sortIPv4Addresses(String[] v, int l, int r) {
    }

}

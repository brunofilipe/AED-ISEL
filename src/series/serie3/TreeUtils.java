package series.serie3;

import java.util.Comparator;

public class TreeUtils {
    /*O(log n)*/
    public static <E> boolean contains(TreeNode<E> root, E min, E max, Comparator<E> cmp){
        if (root==null)return false;
        E value = root.item;
        int small = cmp.compare(min,value);
        int large = cmp.compare(max,value);
        if(small<=0&&large>=0) return true;
        if(small>0)return contains(root.right,min,max,cmp);
        return contains(root.left, min, max, cmp);
    }
/************************************************************************/
   public static TreeNode<Integer> createBSTFromRange(int start, int end){
        throw new UnsupportedOperationException();
   }
/************************************************************************/
//TODO : quando chega ao fim de cada ramo da arvore, ver como sair
    public static <E> boolean isBST(TreeNode<E> root, Comparator<E> cmp){
        if(root==null)return false;
        if(root.left == null || root.right == null) return true;
        if (cmp.compare(root.item,root.left.item) <=0 || cmp.compare(root.item,root.right.item) >=0)return false;
        boolean leftside = isBST(root.left,cmp);
        boolean rightside = isBST(root.right,cmp);
        return leftside && rightside;
    }


}

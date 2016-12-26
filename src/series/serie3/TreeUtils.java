package series.serie3;

import java.util.Comparator;

public class TreeUtils {

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
   public static TreeNode<Integer> createBSTFromRange(int start, int end) {
       if (start > end) return null;
       int aux = (start + end) / 2;
       TreeNode<Integer> root = new TreeNode(aux);
       root.left = createBSTFromRange(start, aux - 1);
       root.right = createBSTFromRange(aux + 1, end);
       return root;
   }
    /************************************************************************/
    public static <E> boolean isBST(TreeNode<E> root, Comparator<E> cmp){
        if(root==null)return false;
        if(root.left == null || root.right == null) return true;
        if (cmp.compare(root.item,root.left.item) <=0 || cmp.compare(root.item,root.right.item) >=0)return false;
        boolean leftside = isBST(root.left,cmp);
        boolean rightside = isBST(root.right,cmp);
        return leftside && rightside;
    }
}

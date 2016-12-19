package series.serie3;

/**
 * Created by Bruno on 18/12/2016.
 */
public class TreeNode<E> {
    public E item;
    public TreeNode<E> left;
    public TreeNode<E> right;
    public TreeNode<E> parent;

    public TreeNode(E item) {
        this.item = item;
    }
}


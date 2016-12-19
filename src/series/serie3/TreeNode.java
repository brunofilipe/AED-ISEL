package series.serie3;

public class TreeNode<E> {
    public E item;
    public TreeNode<E> left;
    public TreeNode<E> right;
    public TreeNode<E> parent;

    public TreeNode(E item) {
        this.item = item;
    }
}


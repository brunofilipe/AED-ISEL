package series.serie3;

public class MakeTree {
    public static TreeNode<Integer> root,rootTest, rootNull = null;

    public static void createTree() {
        /*BST*/
        TreeNode<Integer>
                tnode_0 = new TreeNode<>(0),
                tnode_1 = new TreeNode<>(1),
                tnode_2 = new TreeNode<>(2),
                tnode_3 = new TreeNode<>(3),
                tnode_4 = new TreeNode<>(4),
                tnode_5 = new TreeNode<>(5),
                tnode_6 = new TreeNode<>(6),
                tnode_8 = new TreeNode<>(8),
                tnode_9 = new TreeNode<>(9),
                tnode_10 = new TreeNode<>(10),
                tnode_11 = new TreeNode<>(11),
                tnode_12 = new TreeNode<>(12),
                tnode_13 = new TreeNode<>(13);

        root = new TreeNode<Integer>(7);

        root.left = tnode_3;
        root.right = tnode_11;
        tnode_3.left = tnode_1;
        tnode_3.right = tnode_5;
        tnode_11.left = tnode_9;
        tnode_11.right = tnode_13;
        tnode_1.left = tnode_0;
        tnode_1.right = tnode_2;
        tnode_5.left = tnode_4;
        tnode_5.right = tnode_6;
        tnode_9.left = tnode_8;
        tnode_9.right = tnode_10;
        tnode_13.left = tnode_12;
        tnode_3.parent = tnode_11.parent = root.parent = root;
        tnode_1.parent = tnode_5.parent = tnode_3;
        tnode_9.parent = tnode_13.parent = tnode_11;
        tnode_0.parent = tnode_2.parent = tnode_1;
        tnode_4.parent = tnode_6.parent = tnode_5;
        tnode_8.parent = tnode_10.parent = tnode_9;
        tnode_12.parent = tnode_13;

        /* Not Bst*/
        TreeNode<Integer>
                node_1 = new TreeNode<>(1),
                node_3 = new TreeNode<>(3),
                node_5 = new TreeNode<>(5),
                node_9 = new TreeNode<>(9),
                node_11 = new TreeNode<>(11),
                node_13 = new TreeNode<>(13);

        rootTest = new TreeNode<Integer>(7);

        rootTest.left = node_11;
        rootTest.right = node_3;
        node_3.left = node_5;
        node_3.right = node_1;
        node_11.left = node_13;
        node_11.right = node_9;
        node_3.parent = node_11.parent = rootTest.parent = rootTest;
    }
}

package com.alvinmd.androidrplbo.tree.treexplicit;

public class BinaryTreeApp {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        TreeNode node;

        node = new TreeNode(5);
        tree.insert(node);

        node = new TreeNode(3);
        tree.insert(node);

        node = new TreeNode(4);
        tree.insert(node);

        System.out.print("Traversal dengan preorder :");
        tree.preOrder();
        System.out.print("\nTraversal dengan inorder :");
        tree.inOrder();
        System.out.print("\nTraversal dengan postorder :");
        tree.postOrder();
        System.out.println();

    }
}

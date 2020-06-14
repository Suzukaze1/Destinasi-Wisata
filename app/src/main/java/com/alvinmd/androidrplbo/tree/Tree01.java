package com.alvinmd.androidrplbo.tree;

import java.util.Stack;

public class Tree01 {
    public static class TreeNode
    {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode(char data)
        {
            this.data=data;
        }
    }
    // Recursive Solution
    public void preorder(TreeNode root) {
        if(root !=  null) {
            //Visit the node-Printing the node data
            System.out.printf("%c ",root.data);
            preorder(root.left);
            preorder(root.right);
        }
    }
    // Recursive Solution
    public void postOrder(TreeNode root) {
        if(root !=  null) {
            postOrder(root.left);
            postOrder(root.right);
            //Visit the node by Printing the node data
            System.out.printf("%c ",root.data);
        }
    }
    // Recursive Solution
    public void inOrder(TreeNode root) {
        if(root !=  null) {
            inOrder(root.left);
            //Visit the node by Printing the node data
            System.out.printf("%c ",root.data);
            inOrder(root.right);
        }
    }
    public static void main(String[] args)
    {
        Tree01 bi=new Tree01();
        // Creating a binary tree
        TreeNode rootNode=createBinaryTree();
        System.out.println("Tampilan PreOrder Tree :");
        bi.preorder(rootNode);
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("Tampilan PostOrder Tree :");
        bi.postOrder(rootNode);
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("Tampilan InOrder Tree:");
        bi.inOrder(rootNode);
    }
    public static TreeNode createBinaryTree()
    {
        TreeNode rootNode =new TreeNode('A');
        TreeNode nodeB=new TreeNode('B');
        TreeNode nodeC=new TreeNode('C');
        TreeNode nodeD=new TreeNode('D');
        TreeNode nodeE=new TreeNode('E');
        TreeNode nodeF=new TreeNode('F');
        TreeNode nodeG=new TreeNode('G');
        TreeNode nodeH=new TreeNode('H');
        TreeNode nodeI=new TreeNode('I');
        TreeNode nodeJ=new TreeNode('J');


        rootNode.left=nodeB;
        rootNode.right=nodeC;
        nodeB.left=nodeD;
        nodeB.right=nodeE;
        nodeC.left=nodeF;
        nodeD.left=nodeG;
        nodeD.right=nodeH;
        nodeE.left=nodeI;
        nodeF.right=nodeJ;
        return rootNode;
    }
}

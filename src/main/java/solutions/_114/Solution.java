package solutions._114;

import study.BinaryTree;
import utils.TreeNode;

/**
 * 114. Flatten Binary Tree to Linked List
 */
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode right = root.right;
        
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, -1, -1, 4, -1, -1, 5, -1, 6, -1, -1};
        BinaryTree binaryTree = new BinaryTree(arr);
        TreeNode root = binaryTree.Create();
        binaryTree.preOrder(root);
    }
}
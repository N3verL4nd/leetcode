package solutions._94;


import utils.TreeNode;

import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        /*char[] str = "AB,,CD,,E,,".toCharArray();
        BinaryTree binaryTree = new BinaryTree(str);
        TreeNode root = binaryTree.Create();
        binaryTree.inOrder(root);
        System.out.println();
        System.out.println(new Solution().inorderTraversal(root));*/
    }
}
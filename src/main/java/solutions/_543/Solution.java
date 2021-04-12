package solutions._543;

import utils.TreeNode;
import utils.TreeUtil;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    int nodeCount = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return nodeCount - 1;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = dfs(root.left);
        int rightDepth = dfs(root.right);
        nodeCount = Math.max(nodeCount, leftDepth + rightDepth + 1);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtil.createTree(1, 2, 4, null, null, 5, null, null, 3, null, null);
        System.out.println(new Solution().diameterOfBinaryTree(tree));
    }
}
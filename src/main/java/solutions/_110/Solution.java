package solutions._110;

import utils.TreeNode;

/**
 * 110. Balanced Binary Tree
 */
class Solution {

    private boolean flag = true;

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (!flag) {
            return -1;
        }
        int leftDepth = depth(root.left) + 1;
        int rightDepth = depth(root.right) + 1;

        if (Math.abs(leftDepth - rightDepth) > 1) {
            flag = false;
            return -1;
        }

        return Math.max(leftDepth, rightDepth);
    }

    public boolean isBalanced(TreeNode root) {
        depth(root);
        return flag;
    }
}
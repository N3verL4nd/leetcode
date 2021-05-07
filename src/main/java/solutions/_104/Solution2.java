package solutions._104;

import utils.TreeNode;

/**
 * 104. Maximum Depth of Binary Tree
 */

public class Solution2 {
    private int maxDepth = 0;

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return maxDepth;
        }
        dfs(root, 1);
        return maxDepth;
    }

    private void dfs(TreeNode root, int curDepth) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            maxDepth = Math.max(maxDepth, curDepth);
            return;
        }
        dfs(root.left, curDepth + 1);
        dfs(root.right, curDepth + 1);
    }
}
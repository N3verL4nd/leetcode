package solutions._104;

import utils.TreeNode;

/**
 * 104. Maximum Depth of Binary Tree
 */

public class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int m = maxDepth(root.left);        // 求左子树的最大深度
        int n = maxDepth(root.right);       // 求右子树的最大深度
        return (m > n) ? (m + 1) : (n + 1); // 加上根结点
    }
}
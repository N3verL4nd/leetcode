package solutions._687;

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

    private int max = 0;
    private int cur = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }

    private void dfs(TreeNode root) {
        max = Math.max(max, cur);
        if (root.left != null) {
            if (root.val == root.left.val) {
                cur++;
                dfs(root.left);
            } else {
                cur = 0;
                dfs(root.left);
            }
        }

        if (root.right != null) {
            if (root.val == root.right.val) {
                cur++;
                dfs(root.right);
            } else {
                cur = 0;
                dfs(root.right);
            }
        }


    }


    public static void main(String[] args) {
//        new Solution().longestUnivaluePath(TreeUtil.createTreeLevelOrder(5, 4, 5, 1, 1, null, 5));
//        System.out.println(new Solution().longestUnivaluePath(TreeUtil.createTreeLevelOrder(1, 4, 5, 4, 4, 5, null)));
        System.out.println(new Solution().longestUnivaluePath(TreeUtil.createTreeLevelOrder(1, null, 1, 1, 1, 1, 1, 1)));
    }
}
package solutions._437;

import utils.TreeNode;
import utils.TreeUtil;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int targetSum) {

        return 0;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtil.createTreeLevelOrder(10, 5, -3, 3, 2, null, 11, 3, -2, null, 1);
        System.out.println(new Solution().pathSum(tree, 8));
    }
}
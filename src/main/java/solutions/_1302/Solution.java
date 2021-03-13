package solutions._1302;

import utils.BinaryTreeUtil;
import utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

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
    public int deepestLeavesSum(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return sum;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                if (root.left == null && root.right == null) {
                    levelSum += root.val;
                }
                if (root.left != null) {
                    queue.offer(root.left);
                }

                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
            sum = levelSum;
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeNode root = BinaryTreeUtil.create(1, 2, 3, 4, 5, null, 6, 7, null, null, null, null, 8);
        System.out.println(new Solution().deepestLeavesSum(root));
    }
}
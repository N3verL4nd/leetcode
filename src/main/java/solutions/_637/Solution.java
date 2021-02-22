package solutions._637;

import utils.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 637. Average of Levels in Binary Tree
 */
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return null;
        List<Double> result = new LinkedList<>();


        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int len;
        while (!queue.isEmpty()) {
            len = queue.size();
            double sum = 0;
            for (int i = 0; i < len; i++) {
                root = queue.poll();
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
                sum += root.val;
            }
            result.add(sum / len);

        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        Solution solution = new Solution();
        System.out.println(solution.averageOfLevels(node1));
    }
}
package solutions._129;

import utils.TreeNode;

/**
 * 129. Sum Root to Leaf Numbers
 */
class Solution {
    private int sum;

    public void DFS(TreeNode root, int value) {
        if (root.left == null && root.right == null) {
            sum += (value * 10 + root.val);
            return;
        }

        if (root.left != null) {
            DFS(root.left, value * 10 + root.val);
        }

        if (root.right != null) {
            DFS(root.right, value * 10 + root.val);
        }
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        DFS(root, 0);
        return sum;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        Solution solution = new Solution();
        System.out.println(solution.sumNumbers(node1));
    }
}
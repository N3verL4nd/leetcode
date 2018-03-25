package solutions._112;

import study.BinaryTree;
import utils.TreeNode;

/**
 * 112. Path Sum
 */
class Solution {
    private boolean flag;

    public void DFS(TreeNode root, int cur, int sum) {
        if (root.left == null && root.right == null) {
            if (cur + root.val == sum) {
                flag = true;
                return;
            }
        }
        if (!flag && root.left != null) {
            DFS(root.left, cur + root.val, sum);
        }
        if (!flag && root.right != null) {
            DFS(root.right, cur + root.val, sum);
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        DFS(root, 0, sum);
        return flag;
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 11, 7, -1, -1, 2, -1, -1, 8, 13, -1, -1, 4, -1, 1, -1, -1};
        BinaryTree binaryTree = new BinaryTree(arr);
        TreeNode root = binaryTree.Create();
        System.out.println(new Solution().hasPathSum(root, 22));
    }
}
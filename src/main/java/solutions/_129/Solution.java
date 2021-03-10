package solutions._129;

import utils.TreeNode;
import utils.TreeUtil;

import java.util.LinkedList;
import java.util.Queue;

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

    public int sumNumbersBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = 0;
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left == null && root.right == null) {
                result += root.val;
            }
            if (root.left != null) {
                root.left.val += root.val * 10;
                queue.add(root.left);
            }
            if (root.right != null) {
                root.right.val += root.val * 10;
                queue.add(root.right);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, -1, -1, 3, -1, -1};
        TreeNode root = TreeUtil.createTree(arr);
        System.out.println(new Solution().sumNumbersBFS(root));
    }
}
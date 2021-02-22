package solutions._513;

import utils.TreeNode;
import utils.TreeUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 513. Find Bottom Left Tree Value
 */
class Solution {

    /**
     * 求二叉树的深度
     *
     * @param root 树根结点
     * @return
     */
    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = depth(root.left) + 1;
        int rightDepth = depth(root.right) + 1;
        return Math.max(leftDepth, rightDepth);
    }

    public int findBottomLeftValue(TreeNode root) {
        int height = depth(root);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            height--;
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                if (height == 0) {
                    return root.val;
                }
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
            }

        }
        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeUtil.createTree(1, 2, 4, -1, -1, -1, 3, 5, 7, -1, -1, -1, 6, -1, -1);
        System.out.println(solution.findBottomLeftValue(root));

    }
}
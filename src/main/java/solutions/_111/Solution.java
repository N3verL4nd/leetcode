package solutions._111;


import utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. Minimum Depth of Binary Tree
 */
class Solution {
    private int depth = Integer.MAX_VALUE;

    public void DFS(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (i < depth) {
                depth = i;
            }
        }
        if (root.left != null) {
            DFS(root.left, i + 1);
        }
        if (root.right != null) {
            DFS(root.right, i + 1);
        }
    }

    // 深度优先遍历
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        DFS(root, 1);
        return depth;
    }

    // 广度优先遍历
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        root.val = 1;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();

            if (p.left == null && p.right == null) {
                return p.val;
            }
            if (p.left != null) {
                p.left.val = p.val + 1;
                queue.add(p.left);
            }
            if (p.right != null) {
                p.right.val = p.val + 1;
                queue.add(p.right);
            }
        }
        return 0;
    }
}
package solutions._112;

import utils.TreeNode;
import utils.TreeUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 112. Path Sum
 */
class Solution {
    private boolean flag = false;

    public void DFS(TreeNode root, int cur, int sum) {
        if (flag) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (cur + root.val == sum) {
                flag = true;
                return;
            }
        }
        if (root.left != null) {
            DFS(root.left, cur + root.val, sum);
        }
        if (root.right != null) {
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

    // 广度优先搜索
    public boolean hasPathSumBFS(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left == null && root.right == null && root.val == sum) {
                return true;
            }
            if (root.left != null) {
                root.left.val += root.val;
                queue.add(root.left);
            }
            if (root.right != null) {
                root.right.val += root.val;
                queue.add(root.right);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Integer[] arr = {5, 4, 11, 7, -1, -1, 2, -1, -1, -1, 8, 13, -1, -1, 4, -1, 1, -1, -1};
        TreeNode root = TreeUtil.createTree(arr);
//        System.out.println(new Solution().hasPathSum(root, 22));
        System.out.println(new Solution().hasPathSumBFS(root, 22));
        TreeUtil.levOrder(root);
    }
}
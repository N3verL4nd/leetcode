package solutions._101;

import utils.TreeNode;
import utils.TreeUtil;

import java.util.LinkedList;
import java.util.Objects;
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return isMirror(root.left, root.right);
    }


    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return Objects.equals(left.val, right.val) && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }


    private boolean isMirror2(TreeNode left, TreeNode right) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(left);
        queue.offer(right);
        while (!queue.isEmpty()) {
            left = queue.poll();
            right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtil.createTree(1, 2, 3, null, null, 4, null, null, 2, 4, null, null, 3, null, null);
        System.out.println(new Solution().isSymmetric(tree));
    }
}


/*

给定一个二叉树，检查它是否是镜像对称的。
例如，二叉树[1,2,2,3,4,4,3] 是对称的。

    1
   / \
  2   2
 / \ / \
3  4 4  3
但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
    1
   / \
  2   2
   \   \
   3    3

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/symmetric-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
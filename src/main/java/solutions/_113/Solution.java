package solutions._113;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 113. Path Sum II
 */
class Solution {
    private List<List<Integer>> result = new LinkedList<>();
    private LinkedList<Integer> cur = new LinkedList<>();

    private void DFS(TreeNode root, int curSum, int sum) {
        // 把当前结点放入链表
        cur.addLast(root.val);

        // 访问到叶子结点
        if (root.left == null && root.right == null) {
            // 判断当前的和是否和最终的结点的和相等
            if (curSum + root.val == sum) {
                result.add(new ArrayList<>(cur));
            }
            return;
        }

        // 遍历左子树
        if (root.left != null) {
            DFS(root.left, curSum + root.val, sum);
            cur.removeLast();
        }

        // 遍历右子树
        if (root.right != null) {
            DFS(root.right, curSum + root.val, sum);
            cur.removeLast();
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return new ArrayList<>();
        }
        DFS(root, 0, sum);
        return result;
    }
}
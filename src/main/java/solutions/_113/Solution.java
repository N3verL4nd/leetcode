package solutions._113;

import study.BinaryTree;
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
        DFS(root, 0, sum);
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 11, 7, -1, -1, 2, -1, -1, -1, 8, 13, -1, -1, 4, 5, -1, -1, 1, -1, -1};
        BinaryTree binaryTree = new BinaryTree(arr);
        TreeNode root = binaryTree.Create();
        //binaryTree.preOrder(root);
        System.out.println(new Solution().pathSum(root, 22));
    }
}
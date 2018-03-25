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
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            cur.addLast(root.val);
            if (curSum + root.val == sum) {
                result.add(new ArrayList<>(cur));
            }
            return;
        }

        cur.addLast(root.val);

        if (root.left != null) {
            DFS(root.left, curSum + root.val, sum);
            cur.removeLast();
        }
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
package solutions._102;

import utils.TreeNode;
import utils.TreeUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. Binary Tree Level Order Traversal
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                list.add(root.val);
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        /*TreeNode root = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;*/

        TreeNode root = TreeUtil.createTree(3, 9, 15, -1, -1, 7, -1, -1, 20, -1, -1, -1);


        Solution solution = new Solution();
        System.out.println(solution.levelOrder(root));
    }
}
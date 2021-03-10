package solutions._103;

import utils.TreeNode;
import utils.TreeUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                if (flag) {
                    list.addLast(root.val);
                } else {
                    list.addFirst(root.val);
                }
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
            }
            flag = !flag;
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtil.createTree(3, 9, 15, null, null, 7, null, null, 20, null, null);

        Solution solution = new Solution();
        System.out.println(solution.zigzagLevelOrder(root));
    }
}

/*

            3
          /   \
         9     20
        / \
       15  7
 */
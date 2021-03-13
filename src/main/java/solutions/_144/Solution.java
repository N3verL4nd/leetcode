package solutions._144;

import utils.TreeNode;
import utils.TreeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 前序遍历二叉树
 */
class Solution {
    private List<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                result.add(root.val);
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                root = root.right;
            }
        }
        return result;
    }

    public List<Integer> preorderTraversalBt(TreeNode root) {
        if (root != null) {
            result.add(root.val);
            preorderTraversalBt(root.left);
            preorderTraversalBt(root.right);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtil.createTree(1, 4, 5, null, null, 3, 7, null, null, 8, null, null, 2, null, 9, null, null);
        Solution solution = new Solution();
        System.out.println(solution.preorderTraversalBt(tree));
        System.out.println(solution.preorderTraversal(tree));
    }
}
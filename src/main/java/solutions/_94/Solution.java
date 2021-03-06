package solutions._94;


import utils.TreeNode;
import utils.TreeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 中序遍历二叉树
 */
class Solution {

    private List<Integer> result = new ArrayList<>();

    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

    public List<Integer> inorderTraversalBt(TreeNode root) {
        if (root == null) {
            return result;
        }
        inorderTraversalBt(root.left);
        result.add(root.val);
        inorderTraversalBt(root.right);
        return result;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtil.createTree(1, 4, 5, null, null, 3, 7, null, null, 8, null, null, 2, null, 9, null, null);
        Solution solution = new Solution();
        System.out.println(solution.inorderTraversalBt(tree));
        System.out.println(solution.inorderTraversal1(tree));
    }
}

/*
            1
           / \
          4   2
         / \   \
        5   3   9
           / \
          7   8
 */
package solutions._653;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 653. Two Sum IV - Input is a BST
 */
class Solution {
    private List<Integer> list = new ArrayList<>(1000);

    private void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            list.add(root.val);
            inOrder(root.right);
        }
    }

    public boolean findTarget(TreeNode root, int k) {
        inOrder(root);
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            if (list.get(i) + list.get(j) > k) {
                j--;
            } else if (list.get(i) + list.get(j) < k) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

    }
}
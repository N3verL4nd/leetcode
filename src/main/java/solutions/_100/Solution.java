package solutions._100;


import utils.TreeNode;

/**
 * 100. Same Tree
 */
class Solution {
    private boolean flag = true;

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p != null && q == null) || (p == null && q != null)) {
            flag = false;
            return false;
        }
        if (flag && p != null && q != null) {
            if (p.val != q.val) {
                flag = false;
                return false;
            }
            isSameTree(p.left, q.left);
            isSameTree(p.right, q.right);
        }
        return flag;
    }

    public static void main(String[] args) {

    }
}
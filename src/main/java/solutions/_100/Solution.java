package solutions._100;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    private boolean flag = true;

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (flag && p != null && q != null) {
            isSameTree(p.left, q.left);
            if (p.val != q.val) {
                flag = false;
            }
            isSameTree(p.right, q.right);
        }
        if ((p != null && q == null) || (p == null && q != null)) {
            flag = false;
        }
        return flag;
    }

    public static void main(String[] args) {

    }
}
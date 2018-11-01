package solutions._98;


class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root != null) {
            return isValidBST(root.left);
        }

        if (root.val > root.left.val && root.val < root.right.val) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}
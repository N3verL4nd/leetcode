package solutions._226;

import utils.TreeNode;
import utils.TreeUtil;

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invert(root.left, root.right);
        return root;
    }

    private void invert(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return;
        }

        if (left == null) {

        } else {

        }



        invert(left.left, right.right);
        invert(left.right, right.left);
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtil.createTree(4, 2, 1, null, null, 3, null, null, 7, 6, null, null, 9, null, null);
        TreeUtil.levOrder(tree);
        tree = new Solution().invertTree(tree);
        TreeUtil.levOrder(tree);
    }
}


/*

翻转一棵二叉树。

示例：

输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/invert-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
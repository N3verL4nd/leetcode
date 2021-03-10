package solutions._145;

import utils.TreeNode;
import utils.TreeUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {

    private List<Integer> result;

    public Solution() {
        this.result = new LinkedList<>();
    }

    public List<Integer> postorderTraversal1(TreeNode root) {
        if (root != null) {
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            result.add(root.val);
        }
        return result;
    }


    public List<Integer> postorderTraversal(TreeNode root) {
        if (root != null) {
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            result.add(root.val);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtil.createTree(1, null, 2, 3, null, null, null);
        System.out.println(new Solution().postorderTraversal(tree));

        TreeUtil.reset();
        tree = TreeUtil.createTree(1, 2, 3, null, null, 4, null, null, 2, 4, null, null, 3, null, null);
        System.out.println(new Solution().postorderTraversal(tree));
    }
}


/*

    1
   / \
  2   2
 / \ / \
3  4 4  3

 */

/*

给定一个二叉树，返回它的 后序遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [3,2,1]
进阶:递归算法很简单，你可以通过迭代算法完成吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
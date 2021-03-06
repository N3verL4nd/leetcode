package solutions._662;

import utils.TreeNode;
import utils.TreeUtil;

import java.util.LinkedList;

/*
          1
         / \
        3   2
       /     \
      5       9
     /         \
    6           7
 */

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        int result = 0;
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        root.val = 1;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            result = Math.max(result, queue.peekLast().val - queue.peekFirst().val + 1);
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    poll.left.val = poll.val * 2;
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    poll.right.val = poll.val * 2 + 1;
                    queue.offer(poll.right);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().widthOfBinaryTree(TreeUtil.createTree(1, 3, 5, null, null, 3, null, null, 2, null, 9, null, null, null)));
        TreeUtil.reset();
        System.out.println(new Solution().widthOfBinaryTree(TreeUtil.createTree(1, 3, 5, null, null, 3, null, null)));
        TreeUtil.reset();
        System.out.println(new Solution().widthOfBinaryTree(TreeUtil.createTree(1, 3, 5, null, null, null, 2, null, null)));
        TreeUtil.reset();
        System.out.println(new Solution().widthOfBinaryTree(TreeUtil.createTree(1, 3, 5, 6, null, null, null, null, 2, null, 9, null, 7, null, null)));
        TreeUtil.reset();
    }
}

/*

[1,1,1,1,null,null,1,1,null,null,1]


给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。

每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。

示例 1:

输入:

           1
         /   \
        3     2
       / \     \
      5   3     9

输出: 4
解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
示例 2:

输入:

          1
         /
        3
       / \
      5   3

输出: 2
解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
示例3:

输入:

          1
         / \
        3   2
       /
      5
      
输出: 2
解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
示例 4:

输入:

          1
         / \
        3   2
       /     \
      5       9
     /         \
    6           7

    1,3,5,6,null,null,null,null,2,null,9,null,7,null,null

输出: 8
解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
注意: 答案在32位有符号整数的表示范围内。


1,3,5,6,null,null,null,null,2,null,9,null,7,null,null

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-width-of-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
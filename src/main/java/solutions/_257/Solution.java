package solutions._257;

import utils.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 257. Binary Tree Paths
 */
class Solution {
    private List<String> result = new LinkedList<>();
    private LinkedList<Integer> cur = new LinkedList<>();

    private void DFS(TreeNode root) {
        if (root == null) {
            return;
        }
        // 叶子结点
        if (root.left == null && root.right == null) {
            cur.addLast(root.val);
            StringBuilder sb = new StringBuilder(cur.size() * 4);
            if (!cur.isEmpty()) {
                sb.append(cur.getFirst());
            }
            Iterator<Integer> it = cur.iterator();
            if (it.hasNext()) {
                it.next();
            }
            while (it.hasNext()) {
                sb.append("->");
                sb.append(it.next());
            }
            result.add(sb.toString());
            return;
        }

        cur.add(root.val);

        if (root.left != null) {
            DFS(root.left);
            if (!cur.isEmpty()) {
                // 回溯
                cur.removeLast();
            }
        }
        if (root.right != null) {
            DFS(root.right);
            if (!cur.isEmpty()) {
                // 回溯
                cur.removeLast();
            }
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        DFS(root);
        return result;
    }
}